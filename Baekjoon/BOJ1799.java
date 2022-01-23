import java.io.*;
import java.util.*;

public class BOJ1799 {
  static int size;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    size = Integer.parseInt(br.readLine());
    boolean[][] possible = new boolean[size][size];
    for (int i = 0; i < size; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        possible[i][j] = Integer.parseInt(st.nextToken()) == 1;
      }
    }

    boolean[] flag_a = new boolean[2*size-1]; // direction: /
    boolean[] flag_b = new boolean[2*size-1]; // direction: \
    int black = recur(0, 0, possible, flag_a, flag_b, 0);
    flag_a = new boolean[2*size-1];
    flag_b = new boolean[2*size-1];
    int white = recur(0, 1, possible, flag_a, flag_b, 0);
    bw.write(Integer.toString(black + white));
    bw.newLine();
    bw.flush();
  }
  static int recur(int row, int col, boolean[][] possible, boolean[] flag_a, boolean[] flag_b, int numBishop) {
    int num = numBishop;
    
    boolean isStartBlack = col % 2 == 0;
    // crnt row:
    for (int c = col; c < size; c += 2) {
      if (!possible[row][c]) continue;
      if (flag_a[row + c]) continue;
      if (flag_b[size - 1 - (row - c)]) continue;
      flag_a[row + c] = true;
      flag_b[size - 1 - (row - c)] = true;
      num = Integer.max(num, recur(row, c, possible, flag_a, flag_b, numBishop + 1));
      flag_a[row + c] = false;
      flag_b[size - 1 - (row - c)] = false;
    }

    isStartBlack = !isStartBlack;
    for (int r = row + 1; r < size; r++) {
      for (int c = isStartBlack ? 0 : 1; c < size; c += 2) {
        if (!possible[r][c]) continue;
        if (flag_a[r + c]) continue;
        if (flag_b[size - 1 - (r - c)]) continue;
        flag_a[r + c] = true;
        flag_b[size - 1 - (r - c)] = true;
        num = Integer.max(num, recur(r, c, possible, flag_a, flag_b, numBishop + 1));
        flag_a[r + c] = false;
        flag_b[size - 1 - (r - c)] = false;
      }
      isStartBlack = !isStartBlack;
    }
    return num;
  }
}
