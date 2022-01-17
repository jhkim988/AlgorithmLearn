import java.io.*;
import java.util.*;

public class BOJ1662 {
  private static class Pair {
    char ch;
    int len;
    boolean isLen;
    Pair(char ch) {
      this.ch = ch;
      this.len = 1;
      this.isLen = false;
    }
    Pair(int len) {
      this.len = len;
      this.isLen = true;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    Stack<Pair> stk = new Stack<>();
    for (char ch : input) {
      if (ch == ')') {
        Pair last = stk.pop();
        if (last.ch == '(') {
          stk.pop();
          continue;
        } 
        int len = last.len;
        while(stk.peek().ch != '(') {
          Pair pop = stk.pop();
          len += pop.len;
        }
        stk.pop();
        Pair start = stk.pop();
        stk.push(new Pair((int) (start.ch - '0') * len));
      } else {
        stk.push(new Pair(ch));
      }
    }
    int len = 0;
    while (!stk.isEmpty()) len += stk.pop().len;
    bw.write(Integer.toString(len));
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
