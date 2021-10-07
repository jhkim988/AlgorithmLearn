import java.io.*;
import java.util.*;

public class BOJ16948 {
  private static class Pair {
    int row;
    int col;
    int move;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
      this.move = 0;
    }
    Pair(int row, int col, int move) {
      this(row, col);
      this.move = move;
    } 
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (this.getClass() != o.getClass()) return false;
      Pair other = (Pair) o;
      if (this.row == other.row && this.col == other.col) return true;
      return false;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int size = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    Pair crnt = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    Pair trgt = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

    int[] rowDi = {0, -2, -2, 0, 2, 2};
    int[] colDi = {-2, -1, 1, 2, 1, -1};

    Queue<Pair> que = new LinkedList<>();
    boolean[][] marked = new boolean[size][size];
    que.add(crnt);
    marked[crnt.row][crnt.col] = true;

    while (!que.isEmpty()) {
      crnt = que.poll();
      if (crnt.equals(trgt)) {
        bw.write(crnt.move + "\n");
        bw.flush();
        return;
      }
      for (int i = 0; i < 6; i++) {
        int nextRow = crnt.row + rowDi[i];
        int nextCol = crnt.col + colDi[i];
        if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
        if (marked[nextRow][nextCol]) continue;
        que.add(new Pair(nextRow, nextCol, crnt.move + 1));
        marked[nextRow][nextCol] = true;
      }
    }

    bw.write("-1\n");
    bw.flush();
  }
}
