import java.io.*;
import java.util.*;

public class BOJ13334 {
  private static class Line implements Comparable<Line> {
    long start, end;
    Line(long start, long end) {
      this.start = start;
      this.end = end;
    }
    @Override
    public int compareTo(Line other) {
      return Long.compare(this.end, other.end);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Line> lines = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      if (hi < lo) {
        int tmp = lo;
        lo = hi;
        hi = tmp;
      }
      lines.add(new Line(lo, hi));
    }
    long d = Long.parseLong(br.readLine());
    
    Collections.sort(lines);
    int max = 0;
    PriorityQueue<Line> pq = new PriorityQueue<>(new Comparator<Line>() {
      @Override
      public int compare(Line l1, Line l2) {
        return Long.compare(l1.start, l2.start);
      }
    });
    for (int ptr = 0; ptr < n; ptr++) {
      if (pq.isEmpty()) {
        pq.add(lines.get(ptr));
      } else {
        if (lines.get(ptr).end <= pq.peek().start + d) {
          pq.add(lines.get(ptr));
        }
      }
      if (pq.peek().start + d < lines.get(ptr).end) {
        pq.poll();
      }
      max = Integer.max(max, pq.size());
    }
    bw.write(Integer.toString(max));
    bw.flush();
  }
}
