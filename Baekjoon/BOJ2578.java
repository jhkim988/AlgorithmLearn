import java.io.*;
import java.util.*;

public class BOJ2578 {
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
    Pair[] pos = new Pair[26];
    for (int i = 1; i <= 5; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= 5; j++) {
        int num = Integer.parseInt(st.nextToken());
        pos[num] = new Pair(i, j);
      }
    }

    int numCall = 0;
    boolean[][] check = new boolean[6][6];
    for (int i = 0; i < 5; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 5; j++) {
        numCall++;
        int call = Integer.parseInt(st.nextToken());
        check[pos[call].row][pos[call].col] = true;
        if (bingo(check) >= 3) {
          bw.write(Integer.toString(numCall));
          bw.newLine();
          bw.flush();
          return;
        }
      }
    }
  }
  static int bingo(boolean[][] check) {
    int num = 0;
    nextRow: for (int i = 1; i <= 5; i++) {
      for (int j = 1; j <= 5; j++) {
        if (!check[i][j]) continue nextRow;
      }
      num++;
    }
    nextCol: for (int j = 1; j <= 5; j++) {
      for (int i = 1; i <= 5; i++) {
        if (!check[i][j]) continue nextCol;
      }
      num++;
    }

    boolean flag = true;
    for (int i = 1; i <= 5; i++) {
      if (!check[i][i]) {
        flag = false;
        break;
      }
    }
    if (flag) num++;
    flag = true;
    for (int i = 1; i <= 5; i++) {
      if (!check[i][6-i]) {
        flag = false;
        break;
      }
    }
    if (flag) num++;
    return num;
  }
}
