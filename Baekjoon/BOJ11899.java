import java.io.*;
import java.util.*;

public class BOJ11899 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    Stack<Character> stk = new Stack<>();
    int cost = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i] == '(') {
        stk.add('(');
      } else {
        if (stk.isEmpty()) {
          cost++;
        } else if (stk.peek() == '(') {
          stk.pop();
        } else {
          stk.add(')');
        }
      }
    }
    cost += stk.size();
    bw.write(cost + "\n");
    bw.flush();
  }
}
