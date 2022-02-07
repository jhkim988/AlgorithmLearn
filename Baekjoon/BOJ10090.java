import java.io.*;
import java.util.*;

public class BOJ10090 {
  private static class SegmentTree {
    int treeSize;
    long[] tree;
    SegmentTree(int[] arr) {
      treeSize = 1;
      while (treeSize < arr.length) treeSize <<= 1;
      treeSize <<= 1;
      tree = new long[treeSize];
    }
    long update(int node, int start, int end, int idx) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return ++tree[node];
      int mid = (start + end) >> 1;
      return tree[node] = update(node*2, start, mid, idx) + update(node*2+1, mid+1, end, idx);
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node*2, start, mid, left, right) + get(node*2+1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    // long answer = useSegTree(arr);
    long answer = useMergeSort(arr);
    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static long useSegTree(int[] arr) {
    SegmentTree sg = new SegmentTree(arr);
    long count = 0;
    for (int i = 0; i < arr.length; i++) {
      count += sg.get(1, 0, arr.length - 1, arr[i], arr.length - 1);
      sg.update(1, 0, arr.length - 1, arr[i] - 1);
    }
    return count;
  }
  static long useMergeSort(int[] arr) {
    return recur(0, arr.length - 1, arr);
  }
  static long recur(int start, int end, int[] arr) {
    if (start == end) return 0;
    int mid = (start + end) >> 1;
    long left = recur(start, mid, arr);
    long right = recur(mid+1, end, arr);
    int[] aux = new int[mid + 1 - start];
    System.arraycopy(arr, start, aux, 0, mid + 1 - start);
    long count = 0;
    int ptr = start;
    int ptr1 = 0;
    int ptr2 = mid + 1;
    while (ptr1 < aux.length && ptr2 <= end) {
      while (ptr1 < aux.length && aux[ptr1] < arr[ptr2]) {
        arr[ptr] = aux[ptr1];
        ptr1++;
        ptr++;
      }
      if (ptr1 >= aux.length) break;
      while (ptr2 <= end && arr[ptr2] < aux[ptr1]) {
        arr[ptr] = arr[ptr2];
        ptr2++;
        ptr++;
        count += aux.length - ptr1;
      }
    }
    while (ptr1 < aux.length) {
      arr[ptr] = aux[ptr1];
      ptr1++;
      ptr++;
    }
    return left + right + count;
  }
}
