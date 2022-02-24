import java.io.*;
import java.util.*;

public class BOJ1306 {
  private static class SegTree {
    int n, size;
    int[] arr, max;
    SegTree(int[] arr) {
      this.arr = arr;
      n = arr.length;
      size = 1;
      while (size < n) size <<= 1;
      size <<= 1;
      max = new int[size];
      init(1, 0, n-1);
    }
    void init(int node, int start, int end) {
      if (start == end) {
        max[node] = arr[start];
        return;
      }
      int mid = (start + end) >> 1;
      init(node<<1, start, mid);
      init(node<<1|1, mid+1, end);
      max[node] = Integer.max(max[node<<1], max[node<<1|1]);
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return Integer.MIN_VALUE;
      if (left <= start && end <= right) return max[node];
      int mid = (start + end) >> 1;
      return Integer.max(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    SegTree sg = new SegTree(arr);
    bw.write(Integer.toString(sg.get(1, 0, n-1, 0, 2*m-2)));    
    for (int i = 1; i + 2*m-2 < n; i++) {
      bw.write(' ');
      bw.write(Integer.toString(sg.get(1, 0, n-1, i, i+2*m-2)));
    }
    bw.newLine();
    bw.flush();
  }
}
