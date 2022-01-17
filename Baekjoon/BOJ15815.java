import java.io.*;
import java.util.*;

public class BOJ15815 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    Stack<Integer> num = new Stack<>();
    for (char ch : input) {
      if ('0' <= ch && ch <= '9') num.push(ch - '0');
      else {
        int second = num.pop();
        int first = num.pop();
        int val = 0;
        switch (ch) {
          case '+': {
            val = first + second;
            break;
          } case '-': {
            val = first - second;
            break;
          } case '*': {
            val = first * second;
            break;
          } default: {
            val = first / second;
          }
        }
        num.add(val);
      }
    }
    bw.write(Integer.toString(num.pop()));
    bw.newLine();
    bw.flush(); 
  }
}
