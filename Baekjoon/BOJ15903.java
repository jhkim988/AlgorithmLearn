import java.io.*;
import java.util.*;

public class BOJ15903 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    PriorityQueue<Long> pq = new PriorityQueue<>();
    while (n-- > 0) {
      pq.add(Long.parseLong(st.nextToken()));
    }
    while (m-- > 0) {
      long a = pq.poll();
      long b = pq.poll();
      pq.add(a+b);
      pq.add(a+b);
    }
    long answer = 0;
    while (!pq.isEmpty()) {
      answer += pq.poll();
    }
    bw.write(Long.toString(answer));
    bw.flush();
  }
}
