import java.io.*;
import java.util.*;

public class BOJ16638 {
  static int max = Integer.MIN_VALUE;
  static int len;
  static char[] data;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    len = Integer.parseInt(br.readLine());
    data = br.readLine().toCharArray();
    boolean[] check = new boolean[len/2];
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
    Deque<Integer> deq = new LinkedList<>();
    boolean[] copy = new boolean[check.length];
    System.arraycopy(check, 0, copy, 0, check.length);
    deq.addFirst(data[0] - '0');
    for (int i = 0; i < check.length; i++) {
      if (copy[i]) {
        int num1 = deq.removeFirst();
        char oper = data[i*2 + 1];
        switch (oper) {
          case '+':
            deq.addFirst(num1 + (data[i*2 + 2] - '0'));
            break;
          case '-':
            deq.addFirst(num1 - (data[i*2 + 2] - '0'));
            break;
          case '*':
            deq.addFirst(num1 * (data[i*2 + 2] - '0'));
            break;
          default:
            deq.addFirst(num1 / (data[i*2 + 2] - '0'));
        }
      } else {
        deq.addFirst(data[i*2 + 2] - '0');
      }
    }

    Deque<Integer> deq2 = new LinkedList<>();
    deq2.addFirst(deq.removeLast());
    for (int i = 0; i < check.length; i++) {
      if (copy[i]) continue;
      char oper = data[i*2 + 1];
      if (oper == '*') {
        deq2.addFirst(deq2.removeFirst() * deq.removeLast());
        copy[i] = true;
      } else if (oper == '/') {
        deq2.addFirst(deq2.removeFirst() / deq.removeLast());
        copy[i] = true;
      } else {
        deq2.addFirst(deq.removeLast());
      }
    }

    deq.addFirst(deq2.removeLast());
    for (int i = 0; i < check.length; i++) {
      if (copy[i]) continue;
      char oper = data[i*2 + 1];
      if (oper == '+') {
        deq.addFirst(deq.removeFirst() + deq2.removeLast());
      } else if (oper == '-') {
        deq.addFirst(deq.removeFirst() - deq2.removeLast());
      }
    }
    return deq.size() == 0 ? deq2.removeLast() : deq.removeLast();
  }
}
