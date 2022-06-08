import java.io.*;
import java.util.*;

public class BOJ10070 {
  private static class SegTree {
    int n;
    int[] arr, max, min;
    int[][] lazy;
    SegTree(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      max = new int[treeSize];
      min = new int[treeSize];
      lazy = new int[treeSize][2];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      lazy[node][0] = lazy[node][1] = -1;
      if (start == end) {
        max[node] = min[node] = arr[start];
        return;
      }
      int mid = (start+end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      max[node] = Integer.max(max[node<<1], max[node<<1|1]);
      min[node] = Integer.min(max[node<<1], min[node<<1|1]);
    }
    void updateLazy(int node, int start, int end) {
      if (lazy[node][0] == -1 && lazy[node][1] == -1) return;
      if (lazy[node][0] != -1) {
        max[node] = Integer.max(max[node], lazy[node][0]);
        min[node] = Integer.max(min[node], lazy[node][0]);
      }
      if (lazy[node][1] != -1) {
        max[node] = Integer.min(max[node], lazy[node][1]);
        min[node] = Integer.min(min[node], lazy[node][1]);
      }

      lazy[node][0] = lazy[node][1] = -1;
    }
    void update(int node, int start, int end, int left, int right, int height) {
      updateLazy(node, start, end);
      if (end < left || right < start) return;
      if (left <= start && end <= right) {
        if (height > 0) lazy[node][0] = height;
        if (height < 0) lazy[node][1] = -height;
        updateLazy(node, start, end);
        return;
      }
      int mid = (start+end) >> 1;
      update(node<<1, start, mid, left, right, height);
      update(node<<1|1, mid+1, end, left, right, height);
      max[node] = Integer.max(max[node<<1], max[node<<1|1]);
      min[node] = Integer.min(min[node<<1], min[node<<1|1]);
    }
    int getMax(int node, int start, int end, int left, int right) {
      updateLazy(node, start, end);
      if (end < left || right < start) return -1;
      if (left <= start && end <= right) return max[node];
      int mid = (start+end) >> 1;
      return Integer.max(getMax(node<<1, start, mid, left, right), getMax(node<<1|1, mid+1, end, left, right));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  }
}
