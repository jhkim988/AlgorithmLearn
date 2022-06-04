import java.io.*;
import java.util.*;

public class BOJ17353 {
  private static class SegTree {
    int n;
    int[] arr;
    long[] seqInit, count;
    SegTree(int[] arr) {
      this.n = arr.length;
      this.arr = arr;
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      seqInit = new long[treeSize];
      count = new long[treeSize];
    }
    void update(int node, int start, int end, int left, int right) {
      if (end < left || right < start) return;
      if (left <= start && end <= right) {
        count[node]++;
        seqInit[node] += start-left+1;
        return;
      }
      int mid = (start+end)>>1;
      update(node<<1, start, mid, left, right);
      update(node<<1|1, mid+1, end, left, right);
    }
    long get(int node, int start, int end, int idx) {
      if (idx < start || end < idx) return 0;
      if (start == end) return seqInit[node];
      int mid = (start+end)>>1;
      long add = seqInit[node] + (idx-start)*count[node];
      if (idx <= mid) return add + get(node<<1, start, mid, idx);
      else return add + get(node<<1|1, mid+1, end, idx);
    }
    void update(int left, int right) {
      update(1, 0, n-1, left, right);
    }
    long get(int idx) {
      return get(1, 0, n-1, idx) + arr[idx];
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

    SegTree sg = new SegTree(arr);
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      if (type == 1) {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        sg.update(left-1, right-1);
      } else {
        int x = Integer.parseInt(st.nextToken());
        bw.write(Long.toString(sg.get(x-1)));
        bw.newLine();
      }
    }
    bw.flush();
  }
}
