import java.io.*;
import java.util.*;

public class BOJ1373 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    int len = input.length();
    int ptr = len - 1;
    
    int[] pow = {1, 2, 4};
    Stack<Integer> stk = new Stack<>();
    while (ptr >= 0) {
      int result = 0;
      int minus = 0;
      for (minus = 0; minus < 3; minus++) {
        if (ptr - minus < 0) break; 
        char ch = input.charAt(ptr - minus);
        if (ch == '1') {
          result += pow[minus];
        }
      }
      stk.push(result);
      ptr -= minus;
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
