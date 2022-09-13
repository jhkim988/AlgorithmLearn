import java.io.*;
import java.util.*;

public class BOJ7490 {
  static char[] operationList = {' ', '+', '-'};
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int n;
  static char[] operation;
  static Queue<Long> que;
  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      n = Integer.parseInt(br.readLine());
      solution();
    }
    bw.flush();
  }
  static void solution() throws IOException {
    operation = new char[n-1];
    que = new LinkedList<>();
    recur(0);
    bw.newLine();
  }
  static void recur(int depth) throws IOException {
    if (depth >= n-1) {
      if (calc() == 0) {
        print();
      }
      return;
    }
    for (int i= 0; i < 3; i++) {
      operation[depth] = operationList[i];
      recur(depth+1);
    }
  }
  static long calc() {
    int ptr = 0;
    for (; ptr < n-1; ptr++) {
      long acc = ptr+1;
      while (ptr < n-1 && operation[ptr] == ' ') {
        ptr++;
        acc = acc*10 + ptr+1;
      }
      que.add(acc);
    }
    if (operation[n-2] != ' ') que.add((long) n);

    long ret = que.poll();
    for (int i = 0; i < n-1; i++) {
      if (operation[i] == ' ') continue;
      if (operation[i] == '+') {
        ret += que.poll();
      } else {
        ret -= que.poll();
      }
    }
    return ret;
  }
  static void print() throws IOException {
    bw.write('1');
    for (int i = 0; i < n-1; i++) {
      bw.write(operation[i]);
      bw.write(Integer.toString(i+2));
    }
    bw.newLine();
  }
}
