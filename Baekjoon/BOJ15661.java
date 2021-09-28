import java.io.*;
import java.util.*;

public class BOJ15661 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[][] table = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        table[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    bw.write(minDiff(table) + "\n");
    bw.flush();
  }  
  static int minDiff(int[][] table) {
    boolean[] marked = new boolean[table.length];
    return recur(0, marked, table);
  }
  static int recur(int idx, boolean[] marked, int[][] table) {
    if (idx >= table.length) {
      int team1 = 0;
      int team2 = 0;
      for (int i = 0; i < marked.length; i++) {
        for (int j = 0; j < marked.length; j++) {
          if (marked[i] && marked[j]) {
            team1 += table[i][j];
          } 
          if (!marked[i] && !marked[j]) {
            team2 += table[i][j];
          }
        }        
      }
      return Math.abs(team1 - team2);
    }
    marked[idx] = true;
    int val1 = recur(idx + 1, marked, table);
    marked[idx] = false;
    int val2 = recur(idx + 1, marked, table);
    return Math.min(val1, val2);
  }
}
