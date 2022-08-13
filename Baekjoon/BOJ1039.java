import java.io.*;
import java.util.*;

public class BOJ1039 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    bw.write(Integer.toString(bfs(n, k)));
    bw.flush();
  }
  static int bfs(int n, int k) {
    int digit = getDigit(n);
    Queue<Integer> que = new LinkedList<>();
    que.add(n);

    for (int x = 0; x < k; x++) {
      Queue<Integer> nextLevel = new LinkedList<>();
      HashSet<Integer> hs = new HashSet<>();
      while (!que.isEmpty()) {
        int crnt = que.poll();
        for (int i = 1; i < digit; i++) {
          for (int j = i+1; j < digit; j++) {
            if (j == digit-1 && get(crnt, i) == 0) continue;
            int next = swap(crnt, i, j);
            if (hs.contains(next)) continue;
            hs.add(next);
            nextLevel.add(next);
          }
        }
      }
      if (nextLevel.size() == 0) return -1;
      que = nextLevel;
    }
    int max = 0;
    while (!que.isEmpty()) {
      int crnt = que.poll();
      if (max < crnt) max = crnt;
    }
    return max;
  }
  static int getDigit(int n) {
    int x = 1;
    while (n != 0) {
      n /= 10;
      x++;
    }
    return x;
  }
  static int get(int n, int i) {
    int ii = 1;
    for (int x = 1; x < i; x++) ii *= 10;
    return n/ii%10;
  }
  static int swap(int n, int i, int j) {
    int ii = 1, jj = 1;
    for (int x = 1; x < i; x++) ii *= 10;
    for (int x = 1; x < j; x++) jj *= 10;
    int numI = (n / ii) % 10;
    int numJ = (n / jj) % 10;
    return n - ii*(numI-numJ) - jj*(numJ-numI);
  }
}
