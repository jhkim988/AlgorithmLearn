import java.io.*;
import java.util.*;

public class BOJ12846 {
  private static class SegTree {
    int n, size;
    int[] arr, min;
    SegTree(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      this.size = 1;
      while (this.size < n) this.size <<= 1;
      this.size <<= 1;
      min = new int[this.size];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        min[node] = arr[start];
        return;
      }
      int mid = (start + end)>>1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      min[node] = Integer.min(min[node<<1], min[node<<1|1]);
    }
    int findMax(int node, int start, int end) {
      if (start == end) {
        return arr[start];
      }
      int mid = (start + end)>>1;
      int leftVal = findMax(node<<1, start, mid);
      int rightVal = findMax(node<<1|1, mid+1, end);
      return Integer.max(min[node]*(end-start+1), Integer.max(leftVal, rightVal));
    }
    int findMax() {
      return findMax(1, 0, n-1);
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
    SegTree sg = new SegTree(arr);
    bw.write(Integer.toString(sg.findMax()));
    bw.newLine();
    bw.flush();
  }  
}
