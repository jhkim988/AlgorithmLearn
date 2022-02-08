import java.io.*;
import java.util.*;

public class BOJ9426 {
  private static class SegmentTreeMax{
    int n, treeSize;
    int[] arr, tree;
    SegmentTreeMax(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = arr[start];
      int mid = (start + end) >> 1;
      int leftChild = init(node*2, start, mid);
      int rightChild = init(node*2+1, mid+1, end);
      return tree[node] = leftChild < rightChild ? rightChild : leftChild;
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return Integer.MIN_VALUE;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      int leftChild = get(node*2, start, mid, left, right);
      int rightChild = get(node*2+1, mid+1, end, left, right);
      return leftChild < rightChild ? rightChild : leftChild; 
    }
    int median(int left, int right) {
      int k = right - left + 1;
      return get(1, 0, n-1, left, left + (k+1)/2 - 1);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    SegmentTreeMax sgm = new SegmentTreeMax(arr);
    long sum = 0;
    for (int left = 0; left+k-1 < n; left++) {
      sum += sgm.median(left, left+k-1);
    }
    bw.write(Long.toString(sum));
    bw.newLine();
    bw.flush();
  }
}
