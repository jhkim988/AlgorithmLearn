import java.io.*;
import java.util.*;

public class BOJ10799 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    int len = input.length();
    int sum = 0;
    Stack<Character> stk = new Stack<>();
    char prev = ' ';
    for (int i = 0; i < len; i++) {
      char ch = input.charAt(i);
      if (ch == '(') {
        stk.push(ch);
      } else if (ch == ')') {
        stk.pop();
        if (prev == ')') { // stick
          sum++;
        } else { // laswer
          sum += stk.size();
        }
      }
      prev = ch;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
}
