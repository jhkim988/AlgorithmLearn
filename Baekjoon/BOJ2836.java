import java.io.*;
import java.util.*;

public class BOJ2836 {
  private static class Pair { // end <- start
    int start, end;
    Pair(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    PriorityQueue<Pair> pqb = new PriorityQueue<>(
      (a, b) -> a.start == b.start ? b.end - a.end : b.start - a.start
    );

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      if (start > end) pqb.add(new Pair(start, end));
    }

    long len = 0;
    if (!pqb.isEmpty()) {
      long min = pqb.peek().end;
      long start = pqb.peek().start;
      while (!pqb.isEmpty()) {
        Pair v = pqb.poll();
        if (v.start >= min) {
          if (v.end < min) min = v.end;
        } else {
          len += start - min;
          min = v.end;
          start = v.start;
        }
      }
      len += start - min;
    }
    bw.write(Long.toString(m + 2*len));
    bw.newLine();
    bw.flush();
  }
}
