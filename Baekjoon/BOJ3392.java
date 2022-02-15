import java.io.*;
import java.util.*;

public class BOJ3392 {
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
    int[] count, numNonZero;
    SegTree(int n) {
      int treeSize = 1;
      while (treeSize < n) treeSize <<= 1;
      treeSize <<= 1;
      count = new int[treeSize];
      numNonZero = new int[treeSize];
    }
    void update(int node, int start, int end, int left, int right, boolean isStart) {
      if (start > right || end < left) return;
      if (left <= start && end <= right) {
        if (isStart) count[node]++;
        else count[node]--;
      } else {
        int mid = (start + end) >> 1;
        update(node<<1, start, mid, left, right, isStart);
        update(node<<1|1, mid+1, end, left, right, isStart);
      }
      if (count[node] > 0) numNonZero[node] = (end-start+1);
      else {
        if (start == end) numNonZero[node] = 0;
        else numNonZero[node] = numNonZero[node<<1] + numNonZero[node<<1|1];
      }
    }
    int get() {
      return numNonZero[1];
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Line> line = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      line.add(new Line(x1, x2, y1, true));
      line.add(new Line(x1, x2, y2, false));
    }
    Collections.sort(line, (a, b) -> (a.y==b.y ? (a.x1==b.x1 ? a.x2-b.x2 : a.x1-b.x1) : a.y-b.y));
    int len = 30_001;
    SegTree sg = new SegTree(len);
    int ptr = 0;
    long area = 0;
    for (int y = 0; y < len; y++) {
      while (ptr < line.size() && line.get(ptr).y == y) {
        Line l = line.get(ptr++);
        sg.update(1, 0, len-1, l.x1, l.x2-1, l.isStart);
      }
      area += sg.get();
      if (ptr >= line.size()) break;
    }
    bw.write(Long.toString(area));
    bw.newLine();
    bw.flush();
  }
}
