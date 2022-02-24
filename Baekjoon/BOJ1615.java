import java.io.*;
import java.util.*;

public class BOJ1615 {
  private static class Pair {
    int start, end;
    Pair(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
  private static class SegTree {
    int size;
    int[] count;
    SegTree(int n) {
      size = 1;
      while (size < n) size <<= 1;
      size <<= 1;
      count = new int[size];
    }
    void update(int node, int start, int end, int idx) {
      if (start > idx || end < idx) return;
      if (start == end) {
        count[node] += 1;
        return;
      }
      int mid = (start + end) >> 1;
      update(node<<1, start, mid, idx);
      update(node<<1|1, mid+1, end, idx);
      count[node] = count[node<<1] + count[node<<1|1];
    }
    int get(int node, int start, int end, int left, int right) {
      if (start > right || end < left) return 0;
      if (left <= start && end <= right) return count[node];
      int mid = (start + end) >> 1;
      return get(node<<1, start, mid, left, right) + get(node<<1|1, mid+1, end, left, right);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Pair> graph = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken())-1;
      int end = Integer.parseInt(st.nextToken())-1;
      graph.add(new Pair(start, end));
    }
    Collections.sort(graph, (a, b) -> a.start != b.start ? a.start-b.start : a.end-b.end);
    SegTree sg = new SegTree(n);
    int num = 0;
    for (Pair p : graph) {
      num += sg.get(1, 0, n-1, p.end+1, n-1);
      sg.update(1, 0, n-1, p.end);
    }

    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
}
