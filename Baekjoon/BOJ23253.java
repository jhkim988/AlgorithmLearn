import java.io.*;
import java.util.*;

public class BOJ23253 {
  private static class Pair implements Comparable<Pair> {
    int peek;
    Stack<Integer> stk;
    Pair(int peek, Stack<Integer> stk) {
      this.peek = peek;
      this.stk = stk;
    }
    @Override
    public int compareTo(Pair other) {
      return this.peek - other.peek;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    for (int i = 0; i < M; i++) {
      int size = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      Stack<Integer> stk = new Stack<>();
      for (int j = 0; j < size; j++) {
        stk.push(Integer.parseInt(st.nextToken()));
      }
      pq.add(new Pair(stk.peek(), stk));
    }

    for (int check = 1; check <= N; check++) {
      Pair crnt = pq.poll();
      if (crnt.peek != check) {
        bw.write("No\n");
        bw.flush();
        return;
      }
      crnt.stk.pop();
      if (!crnt.stk.isEmpty()) pq.add(new Pair(crnt.stk.peek(), crnt.stk));
    }
    bw.write("Yes\n");
    bw.flush();
  }
}
