import java.io.*;
import java.util.*;

public class BOJ13537 {
  private static class MergeSortTree {
    int n, height, treeSize;
    int[] arr;
    int[][] tree;
    MergeSortTree(int n, int[] input) {
      this.n = n;
      this.arr = input;
      while(1 << height < n) height++;
      treeSize = 1 << (height + 1);
      tree = new int[treeSize][];
      init(1, 0, n - 1);
    }
    int[] init(int node, int start, int end) {
      if (start == end) {
        tree[node] = new int[1];;
        tree[node][0] = arr[start];
        return tree[node];
      }
      int mid = (start + end) / 2;
      int[] leftChild = init(node*2, start, mid);
      int[] rightChild = init(node*2+1, mid+1, end);
      tree[node] = new int[leftChild.length + rightChild.length];
      int ptrLeft = 0, ptrRight = 0, ptrParent = 0;
      while (ptrLeft < leftChild.length && ptrRight < rightChild.length) {
        if (leftChild[ptrLeft] < rightChild[ptrRight]) {
          tree[node][ptrParent++] = leftChild[ptrLeft++];
        } else {
          tree[node][ptrParent++] = rightChild[ptrRight++];
        }
      }
      while (ptrLeft < leftChild.length) {
        tree[node][ptrParent++] = leftChild[ptrLeft++];
      }
      while (ptrRight < rightChild.length) {
        tree[node][ptrParent++] = rightChild[ptrRight++];
      }
      return tree[node];
    }
    int get(int node, int start, int end, int left, int right, int k) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) {
        return tree[node].length - upperbound(tree[node], k);
      }
      if (start == end) return tree[node][0] > k ? 1 : 0;
      int sum = 0;
      int mid = (start + end) / 2;
      sum += get(node*2, start, mid, left, right, k);
      sum += get(node*2+1, mid+1, end, left, right, k);
      return sum;
    }
    int query(String q) {
      StringTokenizer st = new StringTokenizer(q);
      int left = Integer.parseInt(st.nextToken());
      int right = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      return get(1, 0, n - 1, left - 1, right - 1, k);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] input = new int[n];
    for (int i = 0; i < n; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    MergeSortTree tree = new MergeSortTree(n, input);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      bw.write(Integer.toString(tree.query(br.readLine())));
      bw.newLine();
    }
    bw.flush();
  }
  static int upperbound(int[] arr, int key) {
    int lo = -1;
    int hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
