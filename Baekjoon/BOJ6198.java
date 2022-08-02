import java.io.*;
import java.util.*;

public class BOJ6198 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] h = new int[n];
    for (int i = 0; i < n; i++) {
      h[i] = Integer.parseInt(br.readLine());
    }

    Stack<Integer> stk = new Stack<>();
    long num = 0;
    for (int i = n-1; i >= 0; i--) {
      while (!stk.isEmpty() && h[i] > h[stk.peek()]) {
        stk.pop();
      }
      if (stk.isEmpty()) {
        num += n-1-i;
      } else {
        num += stk.peek()-1-i;
      }
      stk.push(i);
    }

    bw.write(Long.toString(num));
    bw.flush();
  }
}
