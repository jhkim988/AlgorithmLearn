import java.io.*;
import java.util.*;

public class BOJ12789 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    Queue<Integer> que = new LinkedList<>();
    Stack<Integer> stk = new Stack<>();
    for (int i = 0; i < N; i++) {
      que.offer(Integer.parseInt(st.nextToken()));
    }
    int count = 1;
    while (!que.isEmpty() || (stk.isEmpty() || stk.peek() == count)) {
      while (!stk.isEmpty() && stk.peek() == count) {
        stk.pop();
        count++;
      } 
      while (!que.isEmpty() && que.peek() != count) {
        stk.push(que.poll());
      }
      if (!que.isEmpty()) count++;
    }

    bw.write(count == N ? "Nice\n" : "Sad\n");
    bw.flush();
  }
}
