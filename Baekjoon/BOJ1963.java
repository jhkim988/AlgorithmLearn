import java.io.*;
import java.util.*;

public class BOJ1963 {
  private static class Pair {
    int num;
    int move;
    Pair(int num, int move) {
      this.num = num;
      this.move = move;
    }
  }
  static final int RANGE = 10_000;
  static boolean[] isNotPrime = new boolean[RANGE];
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    primeGenerator();
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- >0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int target = Integer.parseInt(st.nextToken());
      bw.write(bfs(start, target) + "\n");
    }
    bw.flush();
  }
  static void primeGenerator() {
    int ptr = 2;
    while (ptr <= RANGE) {
      for (int i = ptr + ptr; i < RANGE; i += ptr) {
        isNotPrime[i] = true;
      }
      ptr++;
      while(ptr < RANGE && isNotPrime[ptr]) ptr++;
    }
  }
  static String bfs(int start, int target) {
    Queue<Pair> que = new LinkedList<>();
    boolean[] marked = new boolean[RANGE];
    que.add(new Pair(start, 0));
    marked[start] = true;

    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      if (crnt.num == target) {
        return crnt.move + "";
      }

      for (int i = 0; i < 10; i++) {
        if (i == 0) {
          for (int j = 0; j < 3; j++) {
            int changeNum = change(crnt.num, j, i);
            if (isNotPrime[changeNum]) continue;
            if (marked[changeNum]) continue;
            que.add(new Pair(changeNum, crnt.move + 1));
            marked[changeNum] = true;
          }
        } else {
          for (int j = 0; j < 4; j++) {
            int changeNum = change(crnt.num, j, i);
            if (isNotPrime[changeNum]) continue;
            if (marked[changeNum]) continue;
            que.add(new Pair(changeNum, crnt.move + 1));
            marked[changeNum] = true;
          }
        }
      }
    }
    return "Impossible";
  }
  static int change(int src, int digit, int change) {
    int pow = pow(10, digit);
    return src - ((src / pow) % 10) * pow + change * pow;  
  }
  static int pow(int base, int exp) {
    if (exp == 0) return 1;
    if (exp == 1) return base;
    int half = pow(base, exp / 2);
    if (exp % 2 == 0) return half * half;
    else return half * half * base;
  }
}
