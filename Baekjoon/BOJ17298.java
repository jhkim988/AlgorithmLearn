import java.io.*;
import java.util.*;

public class BOJ17298 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    Deque<Integer> deq = new LinkedList<>();
    Stack<Integer> stk = new Stack<>();

    int num = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < num; i++) {
      deq.addFirst(Integer.parseInt(st.nextToken()));
    }

    int target;
    boolean flag;
    while (!deq.isEmpty()) {
      flag = false;
      target = deq.pollLast();
      while (!deq.isEmpty()) {
        int tmp = deq.pollLast();
        if (target < tmp) {
          bw.write(tmp + "\n");
          flag = true;
          deq.addLast(tmp);
          while (!stk.empty()) {
            deq.addLast(stk.pop());
          }
        } else {
          stk.add(tmp);
        }
      }
      if (!flag) {
        bw.write(-1 + "\n");
      }
    }
    bw.flush();
  }
}
