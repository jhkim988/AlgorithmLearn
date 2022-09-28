import java.io.*;
import java.util.*;

public class BOJ1874 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Stack<Integer> stk = new Stack<>();
    StringBuilder sb = new StringBuilder();
    int n = Integer.parseInt(br.readLine());
    int[] seq = new int[n];
    for (int i = 0; i < n; i++) {
      seq[i] = Integer.parseInt(br.readLine());
    }

    int seqPtr = 0;
    int push = 1;
    
    while (push <= n) {
      boolean isInfiniteLoop = true;
      while (stk.isEmpty() || stk.peek() < seq[seqPtr]) {
        stk.push(push++);
        sb.append("+\n");
        isInfiniteLoop = false;
      }
      while (!stk.isEmpty() && stk.peek() == seq[seqPtr]) {
        stk.pop();
        seqPtr++;
        sb.append("-\n");
        isInfiniteLoop = false;
      }
      if (isInfiniteLoop) break;
    }
    if (stk.isEmpty() && push > n) {
      bw.write(sb.toString());
    } else {
      bw.write("NO\n");
    }
    bw.flush();
  }
}
