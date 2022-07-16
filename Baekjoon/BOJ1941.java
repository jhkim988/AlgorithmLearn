import java.io.*;
import java.util.*;

public class BOJ1941 {
  private static class Solution1 {
    static int n, k, num;
    static int[] rowDi = {-1, 1, 0, 0}, colDi ={0, 0, -1, 1};
    static char[][] seat;
    static HashSet<Integer> hs;
    static HashSet<Integer> bits;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      n = 5;
      k = 7;
      num = 0;
      seat = new char[n][n];
      hs = new HashSet<>();
      bits = new HashSet<>();
      for (int i = 0; i < n; i++) {
        seat[i] = br.readLine().toCharArray();
      }
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          hs.add(i*n+j);
          recur(1, seat[i][j] == 'Y' ? 1 : 0);
          hs.remove(i*n+j);
        }
      }
      bw.write(Integer.toString(num));
      bw.flush();
    }
    static void recur(int depth, int numY) {
      if (depth >= k) {
        int bit = 0;
        for (int p : hs) {
          int row = p/n, col = p%n;
          bit |= 1 << (row*n+col);
        }
        if (!bits.contains(bit)) {
          num++;
          bits.add(bit);
        } 
        return;
      }
      Queue<Integer> que = new LinkedList<>();
      for (int p: hs) que.add(p);
      for (int p : que) {
        int row = p/n;
        int col = p%n;
        for (int i = 0; i < 4; i++) {
          int adjRow = row + rowDi[i];
          int adjCol = col + colDi[i];
          if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
          if (hs.contains(adjRow*n+adjCol)) continue;
          hs.add(adjRow*n+adjCol);
          if (seat[adjRow][adjCol] == 'S') {
            recur(depth+1, numY);
          } else {
            if (numY+1 < (k+1)/2) {
              recur(depth+1, numY+1);
            }
          }
          hs.remove(adjRow*n+adjCol);
        }
      }
    }
  }
  private static class Solution2 {
    static int num = 0;
    static char[][] seat;
    static boolean[][] check = new boolean[5][5];
    static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      seat = new char[5][5];
      for (int i = 0; i < 5; i++) seat[i] = br.readLine().toCharArray();
      recur(0, 0);
      bw.write(Integer.toString(num));
      bw.flush();
    }
    static void recur(int depth, int last) {
      if (depth >= 7) {
        if (check()) num++;
        return;
      }
      for (int i = last; i < 25; i++) {
        check[i/5][i%5] = true;
        recur(depth+1, i+1);
        check[i/5][i%5] = false;
      }
    }
    static boolean check() {
      Queue<Integer> que = new LinkedList<>();
      boolean[][] visit = new boolean[5][5];
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (!check[i][j]) continue;
          int numY = seat[i][j] == 'Y' ? 1 : 0;
          int numConnected = 1;
          visit[i][j] = true;
          que.add(i*5+j);
          while (!que.isEmpty()) {
            int crnt = que.poll();
            for (int k = 0; k < 4; k++) {
              int adjRow = crnt/5+rowDi[k];
              int adjCol = crnt%5+colDi[k];
              if (adjRow < 0 || adjRow >= 5 || adjCol < 0 || adjCol >= 5) continue;
              if (!check[adjRow][adjCol] || visit[adjRow][adjCol]) continue;
              if (seat[adjRow][adjCol] == 'Y') numY++;
              if (numY > 3) return false;
              visit[adjRow][adjCol] = true;
              numConnected++;
              que.add(adjRow*5+adjCol);
            }
          }
          if (numY <= 3 && numConnected == 7) return true;
          return false;
        }
      }
      return false;
    }
  }
  public static void main(String[] args) throws IOException {
    String[] str = {};
    Solution1.main(str);
    Solution2.main(str);
  }
}