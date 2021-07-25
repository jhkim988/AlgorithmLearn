import java.io.*;
import java.util.*;

public class BOJ4949 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      char[] tmp; 
      Stack<Character> stk;
      boolean flag = true;
      while (true) {
        tmp = br.readLine().toCharArray();
        if (tmp[0] == '.' && tmp.length == 1) {
          break;
        }        
        stk = new Stack<>();
        flag = true;
        for (char c : tmp) {
          if (c == '(' || c == '[') {
            stk.push(c);
          } else if (c == ')') {
            if (stk.isEmpty()) {
              flag = false;
              break;
            } else if (stk.peek() != '(') {
              flag = false;
              break;
            } else {
              stk.pop();
            }
          } else if (c == ']') {
            if (stk.isEmpty()) {
              flag = false;
              break;
            } else if (stk.peek() != '[') {
              flag = false;
              break;
            } else {
              stk.pop(); 
            }
          }
        }
        if (flag && stk.isEmpty()) {
          bw.write("yes\n");
        } else {
          bw.write("no\n");
        }
      }
      bw.flush();
    } catch (IOException e) {

    }
  }
}
