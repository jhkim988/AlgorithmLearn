import java.io.*;
import java.util.*;

public class BOJ17408 {
  private static class SegmentTree {
    // node: [start, end], store index of maximum value
    int n, treeSize;
    int[] arr, tree;
    SegmentTree(int[] arr) {
      this.n = arr.length+1;
      this.arr = new int[this.n];
      System.arraycopy(arr, 0,  this.arr, 1, arr.length);
      this.arr[0] = Integer.MIN_VALUE;
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
      return tree[node] = arr[leftChild] < arr[rightChild] ? rightChild : leftChild;
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) {
        arr[idx] = val; 
        return tree[node];
      }
      int mid = (start + end) >> 1;
      int leftChild = update(node*2, start, mid, idx, val);
      int rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = arr[leftChild] < arr[rightChild] ? rightChild : leftChild;
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      int leftChild = get(node*2, start, mid, left, right);
      int rightChild = get(node*2+1, mid+1, end, left, right);
      return arr[leftChild] < arr[rightChild] ? rightChild : leftChild;
    }
    void updateQuery(int idx, int val) {
      update(1, 0, n-1, idx, val);
    }
    int maxTwoSumQuery(int left, int right) {
      if (left+1 == right) return arr[left] + arr[right];
      int maxIdx = get(1, 0, n-1, left, right);
      if (maxIdx == left) {
        return arr[maxIdx] + arr[get(1, 0, n-1, left+1, right)];
      }
      if (maxIdx == right) {
        return arr[maxIdx] + arr[get(1, 0, n-1, left, right-1)];
      }
      int leftIdx = get(1, 0, n-1, left, maxIdx-1);
      int rightIdx = get(1, 0, n-1, maxIdx+1, right);
      int secondMaxIdx = arr[leftIdx] < arr[rightIdx] ? rightIdx : leftIdx;
      return arr[maxIdx] + arr[secondMaxIdx];
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
    int q = Integer.parseInt(br.readLine());
    SegmentTree sg = new SegmentTree(arr);
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int idx = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());
        sg.updateQuery(idx, val);
      } else {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        bw.write(Integer.toString(sg.maxTwoSumQuery(left, right)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}