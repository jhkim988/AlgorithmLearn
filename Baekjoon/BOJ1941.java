import java.io.*;
import java.util.*;

public class BOJ1941 {
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