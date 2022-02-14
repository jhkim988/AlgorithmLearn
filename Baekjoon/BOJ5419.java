import java.io.*;
import java.util.*;

public class BOJ5419 {
  private static class Pair {
    int x, y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }
  private static class SegTree {
    int treeSize;
    int[] tree;
    SegTree(int n) {
      treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      tree = new int[treeSize];
    }
    void update(int node, int start, int end, int idx, int add) {
      if (start > idx || end < idx) return;
      if (start == end) {
        tree[node] += add;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx, add);
      update(node<<1|1, mid+1, end, idx, add);
      tree[node] = tree[node<<1] + tree[node<<1|1];
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return tree[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int numIsland = Integer.parseInt(br.readLine());
      ArrayList<Pair> island = new ArrayList<>(numIsland);
      int[] cordiX = new int[numIsland];
      int[] cordiY = new int[numIsland];
      for (int i = 0; i < numIsland; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        cordiX[i] = x;
        cordiY[i] = y;
        island.add(new Pair(x, y));
      } 
      int[] compressionX = compression(cordiX);
      int[] compressionY = compression(cordiY);
      Collections.sort(island, (a, b) -> a.y == b.y ? b.x - a.x : a.y - b.y);

      int n = compressionX.length;
      SegTree sg = new SegTree(n);
      int ptr = 0;
      long count = 0;
      for (int y = 0; y < compressionY.length; y++) {
        while (ptr < numIsland && island.get(ptr).y == compressionY[y]) {
          int x = getCordi(island.get(ptr).x, compressionX);
          count += sg.get(1, 0, n-1, x, n-1);
          sg.update(1, 0, n-1, x, 1);
          ptr++;
        } 
      }
      bw.write(Long.toString(count));
      bw.newLine();
      bw.flush();
    }  
  }
  static int[] compression(int[] arr) {
    if (arr.length == 1) return arr;
    Arrays.sort(arr);
    int[] unique = new int[arr.length];
    unique[0] = arr[0];
    int ptr = 1, ptr1 = 0, ptr2 = 1;
    while (ptr1 < arr.length) {
      while (ptr2 < arr.length && arr[ptr1] == arr[ptr2]) ptr2++;
      if (ptr2 >= arr.length) break;
      ptr1 = ptr2;
      ptr2++;
      unique[ptr++] = arr[ptr1];
    }
    int[] ret = new int[ptr];
    System.arraycopy(unique, 0, ret, 0, ptr);
    return ret;
  }
  static int getCordi(int val, int[] arr) {
    int lo = -1, hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] < val) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
