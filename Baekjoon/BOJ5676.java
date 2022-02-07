import java.io.*;
import java.util.*;

public class BOJ5676 {
  private static class SegmentTree {
    int n, treeSize;
    int[] arr, tree;
    SegmentTree(int[] arr) {
      this.arr = arr;
      n = arr.length;
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
      init(1, 0, n-1);
    }
    int sign(int i) {
      if (i > 0) return 1;
      else if (i < 0) return -1;
      else return 0;
    } 
    int init(int node, int start, int end) {
      if (start == end) return tree[node] = sign(arr[start]);
      int mid = (start + end) / 2;
      int leftChild = init(node*2, start, mid);
      int rightChild = init(node*2+1, mid+1, end);
      return tree[node] = sign(leftChild*rightChild);
    }
    int update(int node, int start, int end, int idx, int val) {
      if (start > idx || end < idx) return tree[node];
      if (start == end) {
        arr[idx] = sign(val);
        return tree[node] = arr[idx];
      }
      int mid = (start + end) / 2;
      int leftChild = update(node*2, start, mid, idx, val);
      int rightChild = update(node*2+1, mid+1, end, idx, val);
      return tree[node] = sign(leftChild*rightChild);
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 1;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) / 2;
      int leftChild = get(node*2, start, mid, left, right);
      int rightChild = get(node*2+1, mid+1, end, left, right);
      return sign(leftChild*rightChild);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    while (input != null) {
      StringTokenizer st = new StringTokenizer(input);
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      SegmentTree tree = new SegmentTree(arr);
      while (k-- > 0) {
        st = new StringTokenizer(br.readLine());
        char ch = st.nextToken().charAt(0);
        if (ch == 'C') {
          // update
          int idx = Integer.parseInt(st.nextToken()) - 1;
          int val = Integer.parseInt(st.nextToken());
          tree.update(1, 0, n-1, idx, val);
        } else {
          // get sign
          int left = Integer.parseInt(st.nextToken()) - 1;
          int right = Integer.parseInt(st.nextToken()) - 1;
          int sign = tree.get(1, 0, n-1, left, right);
          if (sign > 0) bw.write('+');
          else if (sign < 0) bw.write('-');
          else bw.write('0');
        }
      }
      bw.newLine();
      input = br.readLine();
    }
    bw.flush();
  }
}
