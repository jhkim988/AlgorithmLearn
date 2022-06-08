import java.io.*;
import java.util.*;

public class BOJ2161 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Deque<Integer> deq = new LinkedList<>();
    for (int i = 1; i <= n; i++) deq.addLast(i);
    while (!deq.isEmpty()) {
      bw.write(Integer.toString(deq.removeFirst()));
      bw.write(' ');
      if (deq.size() > 0) {
        deq.addLast(deq.removeFirst());
      }
    }
    bw.flush();
  }
}
