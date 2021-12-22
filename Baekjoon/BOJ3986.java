import java.io.*;
import java.util.*;

public class BOJ3986 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    Stack<Character> stk = new Stack<>();
    int count = 0;
    while (N-- > 0) {
      char[] input = br.readLine().toCharArray();
      for (int i = 0; i < input.length; i++) {
        char c = input[i];
        if (!stk.isEmpty() && stk.peek() == c) {
          stk.pop();
        } else {
          stk.add(c);
        }
      }
      if (stk.isEmpty()) count++;
      stk.clear();
    }
    bw.write(count + "\n");
    bw.flush();
  }
}
