import java.io.*;
import java.util.*;

public class BOJ2529 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    char[] sign = new char[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      sign[i] = st.nextToken().charAt(0);
    }
    bw.write(find(sign, true) + "\n");
    bw.write(find(sign, false) + "\n");
    bw.flush();
  }
  static String find(char[] sign, boolean isMax) {
    Deque<Integer> deq = new LinkedList<>();
    boolean[] marked = new boolean[10];
    boolean[] flag = {false};
    int ptr = isMax ? 9 : 0;
    while (0 <= ptr && ptr < 10) {
      deq.addLast(ptr);
      marked[ptr] = true;
      recur(0, sign, marked, deq, flag, isMax);
      if (flag[0]) break;
      marked[ptr] = false;
      deq.removeLast();
      ptr = isMax ? ptr - 1 : ptr + 1;
    }
    StringBuilder sb = new StringBuilder();
    while (!deq.isEmpty()) {
      sb.append(deq.removeFirst());
    }
    return sb.toString();
  }
  static void recur(int depth, char[] sign, boolean[] marked, Deque<Integer> deq, boolean[] flag, boolean isMax) {
    if (flag[0]) return;
    if (depth >= sign.length) {
      flag[0] = true;
      return;
    }
    int ptr = isMax ? 9 : 0;
    while (0 <= ptr && ptr < 10) {
      if (marked[ptr]) {
        ptr = isMax ? ptr - 1 : ptr + 1;
        continue;
      }
      if (sign[depth] == '<') {
        if (deq.peekLast() >= ptr) {
          ptr = isMax ? ptr - 1 : ptr + 1;
          continue;
        }
      } else {
        if (deq.peekLast() <= ptr) {
          ptr = isMax ? ptr - 1 : ptr + 1;
          continue;
        }
      } 
      marked[ptr] = true;
      deq.addLast(ptr);
      recur(depth + 1, sign, marked, deq, flag, isMax);
      if (flag[0]) return;
      marked[ptr] = false;
      deq.removeLast();
      ptr = isMax ? ptr - 1 : ptr + 1;
    }
  }
}
