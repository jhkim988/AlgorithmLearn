import java.io.*;
import java.util.*;

public class BOJ5606 {
  private static final int len = 10_001;
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
      count = new long[1<<15];
      sum = new long[1<<15];
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
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int type = Integer.parseInt(st.nextToken());
    ArrayList<Line> vertical = new ArrayList<>();
    ArrayList<Line> horizontal = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      vertical.add(new Line(x1, x2, y1, true));
      vertical.add(new Line(x1, x2, y2, false));
      horizontal.add(new Line(y1, y2, x1, true));
      horizontal.add(new Line(y1, y2, x2, false));
    }
    Collections.sort(vertical, new Comparator<Line>(){
      @Override
      public int compare(Line o1, Line o2) {
        if (o1.y != o2.y) return o1.y - o2.y;
        if (o1.isStart) return -1;
        if (o2.isStart) return 1;
        return o1.x2==o2.x2 ? o1.x1-o2.x1 : o1.x2-o2.x2;
      } 
    });
    Collections.sort(horizontal, new Comparator<Line>(){
      @Override
      public int compare(Line o1, Line o2) {
        if (o1.y != o2.y) return o1.y - o2.y;
        if (o1.isStart) return -1;
        if (o2.isStart) return 1;
        return o1.x2==o2.x2 ? o1.x1-o2.x1 : o1.x2-o2.x2;
      } 
    });
    bw.write(Long.toString(area(vertical)));
    bw.newLine();
    if (type == 2) {
      bw.write(Long.toString(perimetric(vertical, horizontal)));
      bw.newLine();
    }
    bw.flush();
  }
  static long perimetric(ArrayList<Line> vertical, ArrayList<Line> horizontal) {
    return sweepPerimetric(vertical) + sweepPerimetric(horizontal);
  }
  static long sweepPerimetric(ArrayList<Line> lines) {
    long sum = 0;
    long prev = 0;
    SegTree sg = new SegTree();
    for (int i = 0; i < lines.size(); i++) {
      Line l = lines.get(i);      
      sg.update(1, 0, len-1, l.x1, l.x2-1, l.isStart);
      long get = sg.get();
      sum += prev > get ? prev - get : get - prev;
      prev = get;
    }
    return sum;
  }
  static long area(ArrayList<Line> vertical) {
    SegTree sg = new SegTree();
    long area = 0;
    int ptr = 0;
    for (int y = 0; y < len; y++) {
      while (ptr < vertical.size() && vertical.get(ptr).y == y) {
        Line l = vertical.get(ptr++);
        sg.update(1, 0, len-1, l.x1, l.x2-1, l.isStart);
      }
      area += sg.get();
      if (ptr >= vertical.size()) break;
    }
    return area;
  }
}
