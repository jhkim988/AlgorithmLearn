import java.io.*;
import java.util.*;

public class BOJ2210 {
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  static int[][] board;
  static final int size = 5;
  static final int numMove = 6;
  static HashSet<Integer> hs;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    board = new int[size][size];
    for (int i = 0; i < size; i++) {
      StringTokenizer st= new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    hs = new HashSet<>();
    int[] record = new int[numMove];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        // start at board[i][j]
        recur(i, j, 0, record);
      }
    }

    bw.write(hs.size() + "\n");
    bw.flush();
  }
  static void recur(int rowIdx, int colIdx, int depth, int[] record) {
    if (depth >= numMove) {
      int val = 0;
      for (int i = 0; i < numMove; i++) {
        val = val * 10 + record[i];
      }
      hs.add(val);
      return;
    }    
    record[depth] = board[rowIdx][colIdx];
    for (int i = 0; i < 4; i++) {
      int nextRow = rowIdx + rowDi[i];
      int nextCol = colIdx + colDi[i];
      if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
      recur(nextRow, nextCol, depth + 1, record);
    }
  }
}
