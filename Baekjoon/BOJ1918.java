import java.io.*;
import java.util.*;

public class BOJ1918 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    Stack<String> num = new Stack<>();
    Stack<Character> oper = new Stack<>();
    
    int len = input.length();
    for (int i = 0; i < len; i++) {
      char c = input.charAt(i);
      if ('A' <= c && c <= 'Z') {
        num.push(c + "");
        if (!oper.empty() && (oper.peek() == '*' || oper.peek() == '/')) {
          String second = num.pop();
          String first = num.pop();
          char operator = oper.pop();
          num.push(first + second + operator);
        }
      } else if (c == '+' || c == '-') {
        if (!oper.empty() && (oper.peek() == '+' || oper.peek() == '-')) {
          String second = num.pop();
          String first = num.pop();
          char operator = oper.pop();
          num.push(first + second + operator);
        }
        oper.push(c);
      } else if (c == '*' || c == '/') {
        if (!oper.isEmpty() && (oper.peek() == '*' || oper.peek() == '/')) {
          String second = num.pop();
          String first = num.pop();
          char operator = oper.pop();
          num.push(first + second + operator);
        }
        oper.push(c);
      } else if (c == '(') {
        oper.push(c);
      } else if (c == ')') {
        while (!oper.empty() && oper.peek() != '(') {
          String second = num.pop();
          String first = num.pop();
          char operator = oper.pop();
          num.push(first + second + operator);
        }
        oper.pop();
      }
    }
    
    while (!oper.empty()) {
      String second = num.pop();
      String first = num.pop();
      char operator = oper.pop();
      num.push(first + second + operator);
    }
    bw.write(num.peek() + "\n");
    bw.flush();
  }
}
