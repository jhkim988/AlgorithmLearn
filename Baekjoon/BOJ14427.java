import java.io.*;
import java.util.*;

public class BOJ14427 {
  private static class SegmentTree {
    int n, height;
    int[] arr;
    int[] tree;
    SegmentTree(int n, int[] input) {
      this.n = n;
      this.arr = input;
      while (1 << height < n) height++;
      int treeSize = 1 << (height + 1);
      tree = new int[treeSize];
      init(1, 0, n - 1);
    }
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = start;
      int mid = (start + end) / 2;
      int leftChild = init(node*2, start, mid);
      int rightChild = init(node*2+1, mid+1, end);
      return tree[node] = getMinIdx(leftChild, rightChild);
    }
    void update(int idx, int val) {
      arr[idx - 1] = val;
      update(1, 0, n - 1, idx - 1);
    }
    int update(int node, int start, int end, int idx) {
      if (start > idx || end < idx) return tree[node];
      if (start != end) {
        int mid = (start + end) / 2;
        int leftChild = update(node*2, start, mid, idx);
        int rightChild = update(node*2+1, mid+1, end, idx);
        return tree[node] = getMinIdx(leftChild, rightChild);
      }
      return tree[node];
    }
    int get() {
      return tree[1] + 1;
    }
    int getMinIdx(int idx1, int idx2) {
      int minIdx = 0;
      if (arr[idx1] > arr[idx2]) {
        minIdx = idx2;
      } else if (arr[idx1] < arr[idx2]) {
        minIdx = idx1;
      } else {
        minIdx = idx1 > idx2 ? idx2 : idx1;
      }
      return minIdx;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] input = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }
    SegmentTree sgt = new SegmentTree(n, input);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int idx = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());
        sgt.update(idx, val);
      } else {
        bw.write(Integer.toString(sgt.get()));
        bw.newLine();
      }
    }
    bw.flush();
  }
}