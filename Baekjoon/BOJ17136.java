import java.io.*;
import java.util.*;

public class BOJ17136 {
  static int INF = 100;
  static int[][] board;
  static int[] numRemaind = {5, 5, 5, 5, 5};
  static ArrayList<Pair> alist;
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    board = new int[10][10];
    alist = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 10; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] == 1) alist.add(new Pair(i, j));
      }
    }
    int answer = recur(0);
    bw.write(answer < INF ? Integer.toString(answer) : "-1");
    bw.newLine();
    bw.flush();
  }
  static int recur(int ptr) {
    if (ptr >= alist.size()) return 0;
    Pair crnt = alist.get(ptr);
    int row = crnt.row;
    int col = crnt.col;
    if (board[row][col] != 1) return recur(ptr+1);
    int min = INF;
    for (int add = 0; add <= 4; add++) {
      if (numRemaind[add] <= 0) continue;
      boolean possible = true;
      // for (int i = 0; i <= add; i++) {
      //   if (row + add >= 10 || col + add >= 10) possible = false;
      //   possible = possible && (board[row + i][col + add] == 1);
      //   possible = possible && (board[row + add][col + i] == 1);
      // } // no remain paper, continue(41 line) -> NOT CHECK for small "add"
      for (int i = 0; i <= add; i++) {
        for (int j = 0; j <= add; j++) {
          if (row + i >= 10 || col + j >= 10) possible = false;
          possible = possible && (board[row + i][col + j] == 1);
        }
      }
      if (!possible) break;
      numRemaind[add]--;
      for (int i = 0; i <= add; i++) {
        for (int j = 0; j <= add; j++) {
          board[row + i][col + j] = 0;
        }
      }
      min = Integer.min(min, recur(ptr+1)+1);
      for (int i = 0; i <= add; i++) {
        for (int j = 0; j <= add; j++) {
          board[row + i][col + j] = 1;
        }
      }
      numRemaind[add]++;
    }
    return min;
  }
}
