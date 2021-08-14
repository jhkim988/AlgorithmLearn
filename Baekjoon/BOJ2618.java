import java.io.*;
import java.util.*;

public class BOJ2618 {
  private static class Pair {
    int i;
    int j;
    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
    int dist(Pair other) {
      return Math.abs(this.i - other.i) + Math.abs(this.j - other.j);
    }
    @Override
    public String toString() {
      return "<" + i + ", " + j + ">";
    }
    public String moveString(Pair other) {
      return this + " -> " + other;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (getClass() != o.getClass()) {
        return false;
      }
      Pair other = (Pair) o;
      if (this.i == other.i && this.j == other.j) {
        return true;
      }
      return false;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int W = Integer.parseInt(br.readLine());
    Pair[] cases = new Pair[W + 1];
    for (int i = 1; i <= W; i++) {
      st = new StringTokenizer(br.readLine());
      cases[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    Pair police0 = new Pair(1, 1);
    Pair police1 = new Pair(N, N);
    int[][] dp = new int[W + 1][W + 1]; // dp[i][j]: min distance where last position of (police0, police1) = (i, j)
    dp[1][0] = cases[1].dist(police0);
    dp[0][1] = cases[1].dist(police1);
    for (int i = 2; i <= W; i++) {
      dp[0][i] = dp[0][i - 1] + cases[i - 1].dist(cases[i]);
      dp[i][0] = dp[i - 1][0] + cases[i - 1].dist(cases[i]);
    }
    for (int i = 2; i <= W; i++) {
      dp[1][i] = dp[1][i - 1] + cases[i - 1].dist(cases[i]);
      dp[i][1] = dp[i - 1][1] + cases[i - 1].dist(cases[i]);
    }
    for (int i = 2; i <= W; i++) {
      for (int j = 2; j <= W; j++) {
        if (i == j) continue;
        int caseNum = Math.max(i, j);
        dp[i][j] = Math.min(dp[i - 1][j] + cases[i - 1].dist(cases[caseNum]), dp[i][j - 1] + cases[j - 1].dist(cases[caseNum]));
      }
    } 

    int minI = 0;
    int minJ = W;
    for (int i = 0; i < W; i++) {
      if (dp[minI][minJ] > dp[i][W]) {
        minI = i;
        minJ = W;
      }
      if (dp[minI][minJ] > dp[W][i]) {
        minI = W;
        minJ = i;
      }
    }

    for (int i = 0; i <= W; i++) {
      for (int j = 0; j <= W; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }

    bw.write(dp[minI][minJ] + "\n");
    bw.flush();
  }
  public static void wrong(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int W = Integer.parseInt(br.readLine());
    Pair[] cases = new Pair[W + 1];
    for (int i = 1; i <= W; i++) {
      st = new StringTokenizer(br.readLine());
      cases[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    Pair police0 = new Pair(1, 1);
    Pair police1 = new Pair(N, N);
    int[][] dp = new int[W + 1][2];
    Pair[][] pos = new Pair[W + 1][2]; // pos[i][j]: position of police'j'
    pos[0][0] = police0;
    pos[0][1] = police1;

    dp[1][0] = cases[1].dist(police0);
    dp[1][1] = cases[1].dist(police1);
    if (dp[1][0] < dp[1][1]) {
      pos[1][0] = cases[1];
      pos[1][1] = police1;
    } else {
      pos[1][0] = police0;
      pos[1][1] = cases[1];
    }

    for (int i = 2; i <= W; i++) {
      for (int j = 0; j < 2; j++) {
        int compare1 = dp[i - 1][j] + cases[i - 1].dist(cases[i]);
        int compare2 = dp[i - 1][(j + 1)%2] + pos[i - 1][(j + 1)%2].dist(cases[i]);
        if (compare1 < compare2) {
          pos[i][j] = cases[i];
          pos[i][(j + 1)%2] = pos[i - 1][(j + 1)%2];
          dp[i][j] = compare1;
          // System.out.println(j + " move: " + cases[i - 1].moveString(cases[i]));
        } else {
          pos[i][(j + 1)%2] = cases[i];
          pos[i][j] = pos[i - 1][j]; 
          dp[i][j] = compare2;
          // System.out.println(((j+1)%2) + " move: " + pos[i - 1][(j + 1)%2].moveString(cases[i]));
        }
      }
    }
    System.out.println("dp: ");
    for (int i = 0; i <= W; i++) {
      System.out.println(dp[i][0] + ", " + dp[i][1] + ", " + pos[i][0] + ", " + pos[i][1]);
    }
    
    // trace back
    Stack<Integer> path = new Stack<>();
    for (int i = W; i > 0; i--) {
      if (pos[i][0].equals(pos[i - 1][0])) {
        path.add(2);
      } else {
        path.add(1);
      }
    }

    bw.write(Math.min(dp[W][0], dp[W][1]) + "\n");
    while (!path.isEmpty()) {
      bw.write(path.pop() + "\n");
    }
    bw.flush();
  }
}
