import java.io.*;
import java.util.*;

public class BOJ2185 {
  private static class Line {
    int x1, x2, y;
    boolean isStart;
    Line(int x1, int x2, int y, boolean isStart) {
      this.x1 = x1;
      this.x2 = x2;
      this.y = y;
      this.isStart = isStart;
    }
  }
  private static class SegTree {
    long[] count, sum;
    SegTree() {
      count = new long[1<<16];
      sum = new long[1<<16];
    }
    void update(int node, int start, int end, int left, int right, boolean isStart) {
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        count[node] += isStart ? 1 : -1;
      } else {
        int mid = (start + end) >> 1;
        update(node<<1, start, mid, left, right, isStart);
        update(node<<1|1, mid+1, end, left, right, isStart);
      }
      if (count[node] > 0) {
        sum[node] = end-start+1;
      } else {
        if (start == end) sum[node] = 0;
        else sum[node] = sum[node<<1] + sum[node<<1|1];
      }
    }
    long get() {
      return sum[1];
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Line> vertical = new ArrayList<>();
    ArrayList<Line> horizontal = new ArrayList<>();
    while (n-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken()) + 10_000;
      int y1 = Integer.parseInt(st.nextToken()) + 10_000;
      int x2 = Integer.parseInt(st.nextToken()) + 10_000;
      int y2 = Integer.parseInt(st.nextToken()) + 10_000;
      if (x2 < x1) { 
        int t = x1;
        x1 = x2;
        x2 = t;
      }
      if (y2 < y1) {
        int t = y1;
        y1 = y2;
        y2 = t;
      }
      vertical.add(new Line(y1, y2, x1, true));
      vertical.add(new Line(y1, y2, x2, false));
      horizontal.add(new Line(x1, x2, y1, true));
      horizontal.add(new Line(x1, x2, y2, false)); 
    }
    Collections.sort(vertical, (a, b) -> {
      if (a.y != b.y) return a.y-b.y;
      if (a.isStart && !b.isStart) return -1;
      if (!a.isStart && b.isStart) return 1;
      return a.x2==b.x2 ? a.x1-b.x2 : a.x2-b.x2; 
    });
    Collections.sort(horizontal, (a, b) -> {
      if (a.y != b.y) return a.y-b.y;
      if (a.isStart && !b.isStart) return -1;
      if (!a.isStart && b.isStart) return 1;
      return a.x2==b.x2 ? a.x1-b.x2 : a.x2-b.x2; 
    });
    int len = 20_001;
    long answer = getSweep(vertical, len) + getSweep(horizontal, len);
    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static long getSweep(ArrayList<Line> lines, int n) {
    SegTree sg = new SegTree();
    long sum = 0;
    long prev = 0;
    for (int i = 0; i < lines.size(); i++) {
      Line l = lines.get(i);
      sg.update(1, 0, n-1, l.x1, l.x2-1, l.isStart);
      long get = sg.get();
      sum += prev > get ? prev - get : get - prev;
      prev = get;
    }
    return sum;
  }
}
