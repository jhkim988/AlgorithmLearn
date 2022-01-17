import java.io.*;
import java.util.*;

public class BOJ1374 {
  private static class Pair {
    int start, end;
    Pair(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numLec = Integer.parseInt(br.readLine());
    PriorityQueue<Pair> pq = new PriorityQueue<>((p, q) -> p.start - q.start);
    for (int i = 0; i < numLec; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      Integer.parseInt(st.nextToken()); // id
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      pq.add(new Pair(start, end));
    }
    int needClass = 1;
    PriorityQueue<Pair> pq2 = new PriorityQueue<>((p, q) -> p.end - q.end);
    pq2.add(pq.poll());
    while (!pq.isEmpty()) {
      Pair poll = pq.poll();
      Pair peek = pq2.peek();
      if (peek.end <= poll.start) pq2.poll();
      pq2.add(poll);
      needClass = Integer.max(needClass, pq2.size());
    }

    bw.write(Integer.toString(needClass));
    bw.newLine();
    bw.flush();
  }
}
