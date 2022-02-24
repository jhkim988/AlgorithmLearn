import java.io.*;
import java.util.*;

public class BOJ2504 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    Stack<Character> stk1 = new Stack<>();
    Stack<Long> stk2 = new Stack<>();
    boolean valid = (input.length & 1) == 0;
    long result = 0;
    for (char ch : input) {
      if (ch == '(' || ch == '[') {
        stk1.push(ch);
      } else if (ch == ')') {
        if (stk1.isEmpty() || stk1.peek() == '[') {
          valid = false;
          break;
        }
        if (stk1.peek() == '(') {
          stk1.pop();
          if (!stk1.isEmpty() && stk1.peek() == '-') {
            stk2.push(stk2.pop() + 2);
          } else {
            stk1.push('-');
            stk2.push(2L);
          }          
          continue;
        }
        if (stk1.peek() == '-') {
          int sum = 0;
          while (!stk1.isEmpty() && stk1.peek() == '-') {
            sum += stk2.pop();
            stk1.pop();
          }
          if (stk1.isEmpty() || stk1.peek() == '[') {
            valid = false;
            break;
          }
          if (!stk1.isEmpty() && stk1.peek() == '(') stk1.pop();
          stk2.push(sum * 2L);
          stk1.push('-');
        }        
      } else if (ch == ']') {
        if (stk1.isEmpty() || stk1.peek() == '(') {
          valid = false;
          break;
        }
        if (stk1.peek() == '[') {
          stk1.pop();
          if (!stk1.isEmpty() && stk1.peek() == '-') {
            stk2.push(stk2.pop() + 3);
          } else {
            stk1.push('-');
            stk2.push(3L);
          }
          continue;
        }
        if (stk1.peek() == '-') {
          int sum = 0;
          while (!stk1.isEmpty() && stk1.peek() == '-') {
            sum += stk2.pop();
            stk1.pop();
          }
          if (stk1.isEmpty() || stk1.peek() == '(') {
            valid = false;
            break;
          }
          if (!stk1.isEmpty() && stk1.peek() == '[') stk1.pop();
          stk2.push(sum * 3L);
          stk1.push('-');
        }
      }
    }
    while (!stk1.isEmpty()) {
      if (stk1.pop() != '-') {
        valid = false; break;
      }
    }
    if (valid) {
      while (!stk2.isEmpty()) {
        result += stk2.pop();
      }
      bw.write(Long.toString(result));
    } else {
      bw.write('0');
    }
    bw.newLine();
    bw.flush();
  }
  static <T> void print(Stack<T> stk) {
    Stack<T> tmp = new Stack<>();
    while (!stk.isEmpty()) tmp.push(stk.pop());
    while (!tmp.isEmpty()) {
      T a = tmp.pop();
      System.out.print(a + " ");
      stk.push(a);
    }
    System.out.println();
  }
}
