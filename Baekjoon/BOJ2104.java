import java.io.*;
import java.util.*;

public class BOJ2104 {
  private static class SegTree_Min {
    int treeSize;
    int[] arr, tree;
    SegTree_Min(int n, int[] arr) {
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    public void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = start;
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = arr[tree[node<<1]] < arr[tree[node<<1|1]] ? tree[node<<1] : tree[node<<1|1];
    }
    public int get(int node, int start, int end, int left, int right) {
      if (right < start || end < left) return -1;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      int leftChild = get(node<<1, start, mid, left, right);
      int rightChild = get(node<<1|1, mid+1, end, left, right);
      if (leftChild < 0) return rightChild;
      if (rightChild < 0) return leftChild;
      return arr[leftChild] < arr[rightChild] ? leftChild : rightChild;
    }
  }
  private static class SegTree_Sum {
    int treeSize;
    int[] arr;
    long[] tree;
    SegTree_Sum(int n, int[] arr) {
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
      init(1, 0, n-1);
    }
    public void init(int node, int start, int end) {
      if (start == end) {
        tree[node] = arr[start];
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    public long get(int node, int start, int end, int left, int right) {
      if (right < start || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    // long answer = useDivideConquer(0, n-1, arr);
    // long answer = useSegTree(n, arr);
    long answer = useStack(n, arr);
    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static long useDivideConquer(int start, int end, int[] arr) {
    if (start == end) return ((long) arr[start]) * arr[start];
    int mid = (start + end) >> 1;
    long left = useDivideConquer(start, mid, arr);
    long right = useDivideConquer(mid+1, end, arr);

    int ptr1 = mid;
    int ptr2 = mid+1;
    long sum = arr[ptr1] + arr[ptr2];
    long min = arr[ptr1] < arr[ptr2] ? arr[ptr1] : arr[ptr2];
    long value = sum * min;
    ptr1--; ptr2++;

    while (start <= ptr1 || ptr2 <= end) {
      while (start <= ptr1) {
        if (arr[ptr1] < min) break;
        sum += arr[ptr1];
        ptr1--;
      }
      while (ptr2 <= end) {
        if (arr[ptr2] < min) break;
        sum += arr[ptr2];
        ptr2++;
      }
      value = Long.max(value, sum*min);
      if (start <= ptr1 && ptr2 <= end) {
        if (arr[ptr1] < arr[ptr2]) {
          min = arr[ptr2];
          sum += arr[ptr2];
          ptr2++;
        } else {
          min = arr[ptr1];
          sum += arr[ptr1];
          ptr1--;
        }
      } else if (start <= ptr1) {
        min = arr[ptr1];
        sum += arr[ptr1];
        ptr1--;
      } else if (ptr2 <= end) {
        min = arr[ptr2];
        sum += arr[ptr2];
        ptr2++;
      } else {
        break;
      }
    }
    return Long.max(Long.max(left, right), value);
  }
  static long useSegTree(int n, int[] arr) {
    SegTree_Min sgm = new SegTree_Min(n, arr);
    SegTree_Sum sgs = new SegTree_Sum(n, arr);
    return recur(0, n-1, n, arr, sgm, sgs);
  }
  static long recur(int start, int end, int n, int[] arr, SegTree_Min sgm, SegTree_Sum sgs) {
    if (start == end) return ((long) arr[start]) * arr[start];
    int min = sgm.get(1, 0, n-1, start, end);
    long sum = sgs.get(1, 0, n-1, start, end);
    long value = sum * arr[min];
    long leftValue = start == min ? 0 : recur(start, min-1, n, arr, sgm, sgs);
    long rightValue = end == min ? 0 : recur(min+1, end, n, arr, sgm, sgs);
    return Long.max(Long.max(leftValue, rightValue), value);
  }
  static long useStack(int n, int[] arr) {
    long[] pSum = new long[n];
    pSum[0] = arr[0];
    for (int i = 1; i < n; i++) {
      pSum[i] = pSum[i-1] + arr[i];
    }
    long result = 0;
    Stack<Integer> stk = new Stack<>();
    stk.push(0);
    for (int i = 1; i < n; i++) {
      int peek = stk.peek();
      if (arr[peek] <= arr[i]) {
        stk.push(i);
      } else {
        int right = i-1;
        while (!stk.isEmpty()) {
          long min = arr[stk.peek()];
          if (min < arr[i]) break;
          stk.pop();
          int left = stk.isEmpty() ? -1 : stk.peek()+1;
          long sum = pSum[right] - (left == -1 ? 0 : pSum[left-1]);
          result = Long.max(result, min * sum);
        }
        stk.push(i);
      }
    }

    int right = n-1;
    while (!stk.isEmpty()) {
      long min = arr[stk.pop()];
      int left = stk.isEmpty() ? -1 : stk.peek()+1;
      long sum = pSum[right] - (left==-1 ? 0 : pSum[left-1]);
      result = Long.max(result, min * sum);
    }
    return result;
  }
}
