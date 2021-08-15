import java.io.*;
import java.util.*;

public class BOJ9935 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String str = br.readLine();
    String bomb = br.readLine();

    int len = str.length();
    int bomblen = bomb.length();
    Deque<Character> stk = new LinkedList<>();
    Stack<Character> tmp = new Stack<>();
    for (int i = 0; i < len; i++) {
      stk.push(str.charAt(i));
      if (stk.peek() == bomb.charAt(bomblen - 1)) {
        int bombptr = bomblen - 1;
        while (stk.peek() == bomb.charAt(bombptr)) {
          tmp.push(stk.pop());
          bombptr--;
          if (bombptr < 0) {
            tmp.clear();
            break;
          }
          if (stk.isEmpty()) {
            while (!tmp.empty()) {
              stk.push(tmp.pop());
            }
            break;
          }
        }
        if (bombptr >= 0) {
          while (!tmp.isEmpty()) {
            stk.push(tmp.pop());
          }
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!stk.isEmpty()) {
      sb.append(stk.removeLast());
    }
    if (sb.length() > 0) {
      bw.write(sb.toString() + "\n");
    } else {
      bw.write("FRULA\n");
    }
    bw.flush();
  }
}
