import java.io.*;
import java.util.*;

public class BOJ16637 {
  static int max = Integer.MIN_VALUE;
  static int len;
  static char[] data;
  static Deque<Integer> deq;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    len = Integer.parseInt(br.readLine());
    data = br.readLine().toCharArray();
    boolean[] check = new boolean[len/2];
    deq = new LinkedList<>();
    recur(0, check);
    bw.write(max + "\n");
    bw.flush();
  }
  static void recur(int depth, boolean[] check) {
    if (depth >= check.length) {
      int calc = calc(check);
      if (max < calc) max = calc;
      return;
    }
    recur(depth + 1, check);
    if (depth == 0 || !check[depth - 1]) {
      check[depth] = true;
      recur(depth + 1, check);
      check[depth] = false;
    }
  }
  static int calc(boolean[] check) {
    deq.addFirst((int) (data[0] - '0'));
    for (int i = 0; i < check.length; i++) {
      if (check[i]) {
        int num1 = deq.removeFirst();
        int num2 = data[i*2 + 2] - '0';
        char oper = data[i*2 + 1];
        int result;
        switch (oper) {
          case '+':
            result = num1 + num2;
            break;
          case '-':
            result = num1 - num2;
            break;
          case '*':
            result = num1 * num2;
            break;
          default:
            result = num1 / num2;
        }
        deq.addFirst(result);
      } else {
        deq.addFirst((int) (data[i*2 + 2] - '0'));
      }
    }
    int val = 0;
    for (int i = 0; i < check.length; i++) {
      if (check[i]) continue;
      int num1 = deq.removeLast();
      int num2 = deq.removeLast();
      char oper = data[i*2 + 1];
      int result = 0;
      switch (oper) {
        case '+':
        result = num1 + num2;
        break;
      case '-':
        result = num1 - num2;
        break;
      case '*':
        result = num1 * num2;
        break;
      default:
        result = num1 / num2;
      }
      deq.addLast(result);
    }
    return deq.removeLast();
  }
}
