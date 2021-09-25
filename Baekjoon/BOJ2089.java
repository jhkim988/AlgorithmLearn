import java.io.*;
import java.util.*;

public class BOJ2089 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int input = Integer.parseInt(br.readLine());
    bw.write(minus2Base(input));
    bw.flush();
  }
  static String minus2Base(int num) {
    if (num == 0) {
      return "0\n";
    }
    Stack<Integer> stk = new Stack<>();
    boolean positive = true;
    while (num != 0) {
      int remainder = num % 2;
      if (remainder < 0) remainder = -remainder;
      stk.push(remainder);
      if (positive) {
        num -= remainder;
      } else {
        num += remainder;
      }
      num /= 2;
      positive = !positive;
    }

    StringBuilder sb = new StringBuilder();
    while (!stk.isEmpty()) {
      sb.append(stk.pop());
    }
    sb.append('\n');
    return sb.toString();
  }
}