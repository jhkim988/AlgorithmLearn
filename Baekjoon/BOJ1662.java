import java.io.*;
import java.util.*;

public class BOJ1662 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    Stack<Integer> stk = new Stack<>();
    Stack<Integer> start = new Stack<>();
    char prev = 0;
    for (char ch : input) {
      if (ch == '(') {
        start.push(stk.pop());
        stk.push((int) '(');
      } else if (ch == ')') {
        if (prev == '(') stk.pop();
        else if (prev == ')') {
          int len = 0;
          while (stk.peek() != '(') len += stk.pop();
          stk.pop(); // '('
          stk.push(start.pop() * len);
        } else {
          int len = 0;
          while (stk.peek() != '(') {
            len++;
            stk.pop();
          }
          stk.pop(); // '('
          stk.push(len);
        }
      } else {
        stk.push(ch - '0');
      }
      prev = ch;

      System.out.println("stk: " + stkToString(stk));
      System.out.println("start: " + stkToString(start));
    }

    bw.write(Integer.toString(stk.pop()));
    bw.newLine();
    bw.flush();
  }
  static <T> String stkToString(Stack<T> stk) {
    StringBuilder sb = new StringBuilder();
    Stack<T> tmp = new Stack<>();
    while (!stk.isEmpty()) tmp.add(stk.pop());
    while (!tmp.isEmpty()) {
      T out = tmp.pop();
      stk.push(out);
      sb.append(out.toString()).append(' ');
    }
    return sb.toString();
  } 
}
