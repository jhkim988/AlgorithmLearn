import java.io.*;
import java.util.*;

public class BOJ1212 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    int len = input.length();

    if (len == 1 && input.charAt(0) == '0') {
      bw.write(0 + "\n");
      bw.flush();
      return;
    }

    Stack<Integer> stk = new Stack<>();
    for (int ptr = len - 1; ptr >= 0; ptr--) {
      int num = input.charAt(ptr) - '0';
      int numIter = 0;
      while (num != 0) {
        stk.push(num % 2);
        num /= 2;
        numIter++;
      }
      if (ptr != 0) {
        for (int i = 2; i >= numIter; i--) {
          stk.push(0);
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!stk.isEmpty()) {
      sb.append(stk.pop());
    }
    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }  
}
