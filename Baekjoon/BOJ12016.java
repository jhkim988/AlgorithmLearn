import java.io.*;
import java.util.*;

public class BOJ12016 {
  static class SegmentTreeMin {
    int n, treeSize;
    long[] arr;
    int[] tree;
    SegmentTreeMin(long[] arr) {
      this.n = arr.length;
      this.arr = new long[this.n];
      System.arraycopy(arr, 0, this.arr, 0, arr.length);
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = start;
      int mid = (start + end) >> 1;
      int leftChild = init(node*2, start, mid);
      int rightChild = init(node*2+1, mid+1, end);
      return tree[node] = arr[leftChild] > arr[rightChild] ? rightChild : leftChild; 
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) {
        arr[idx] = val;
        return start;
      }
      int mid = (start + end) >> 1;
      int leftChild = update(node*2, start, mid, idx, val);
      int rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = arr[leftChild] > arr[rightChild] ? rightChild : leftChild; 
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      int leftChild = get(node*2, start, mid, left, right);
      int rightChild = get(node*2+1, mid+1, end, left, right);
      return arr[leftChild] > arr[rightChild] ? rightChild : leftChild;
    }
    int removeMin() {
      int idx = tree[1];
      update(1, 0, n-1, idx, Integer.MAX_VALUE);
      return idx;
    }
  }
  private static class SegmentTreeCount {
    int n, treeSize;
    long[] arr, tree;
    SegmentTreeCount(int n) {
      this.n = n;
      treeSize = 1;
      while (treeSize < this.n) treeSize <<= 1;
      treeSize <<= 1;
      arr = new long[this.n];
      Arrays.fill(arr, 1);
      tree = new long[treeSize];
      init(1, 0, this.n-1);
    }
    long init(int node, int start, int end) {
      if (start == end) return tree[node] = arr[start];
      int mid = (start + end) >> 1;
      long leftChild = init(node*2, start, mid);
      long rightChild = init(node*2+1, mid+1, end);
      return tree[node] = leftChild + rightChild;
    }
    long update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) return tree[node] = arr[start] = val;
      int mid = (start + end) >> 1;
      long leftChild = update(node*2, start, mid, idx, val);
      long rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = leftChild + rightChild;
    }
    long get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      long leftChild = get(node*2, start, mid, left, right);
      long rightChild = get(node*2+1, mid+1, end, left, right);
      return leftChild + rightChild;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    long[] arr = new long[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    SegmentTreeMin sgm = new SegmentTreeMin(arr);
    SegmentTreeCount sgc = new SegmentTreeCount(n);
    long[] time = new long[n];
    int prevIdx = sgm.removeMin();
    int remainWork = n;
    time[prevIdx] = remainWork--*(arr[prevIdx]-1) + sgc.get(1, 0, n-1, 0, prevIdx);
    sgc.update(1, 0, n-1, prevIdx, 0);
    while (remainWork > 0) {
      int idx = sgm.removeMin();
      long count = 0;
      if (arr[idx] == arr[prevIdx]) {
        count = time[prevIdx] + sgc.get(1, 0, n-1, prevIdx, idx);
      } else {
        count = time[prevIdx] + sgc.get(1, 0, n-1, prevIdx, n-1) + remainWork * (arr[idx] - arr[prevIdx] - 1) + sgc.get(1, 0, n-1, 0, idx);
      }
      sgc.update(1, 0, n-1, idx, 0);
      time[idx] = count;
      prevIdx = idx;
      remainWork--;
    }
    for (long t : time) {
      bw.write(Long.toString(t));
      bw.newLine();
    }
    bw.flush();
  }
}