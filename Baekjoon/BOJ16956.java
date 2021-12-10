import java.io.*;
import java.util.*;

public class BOJ16956 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    char[][] map = new char[numRow][numCol];
    ArrayList<Pair> sheepList = new ArrayList<>();
    for (int i = 0; i < numRow; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j = 0; j < numCol; j++) {
        if (map[i][j] == '.') map[i][j] = 'D';
        if (map[i][j] == 'S') sheepList.add(new Pair(i, j));
      }
    }

    boolean isPossible = true;
    outer: for (Pair p : sheepList) {
      for (int i = 0; i < 4; i++) {
        int adjRow = p.row + rowDi[i];
        int adjCol = p.col + colDi[i];
        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) continue;
        if (map[adjRow][adjCol] == 'W') {
          isPossible = false;
          break outer;
        }
      }
    }

    if (isPossible) {
      StringBuilder sb = new StringBuilder();
      sb.append("1\n");
      for (int i = 0; i < numRow; i++) {
        for (int j = 0; j < numCol; j++) {
          sb.append(map[i][j]);
        }
        sb.append('\n');
      }
      bw.write(sb.toString());
    } else {
      bw.write("0\n");
    }
    bw.flush();
  }
}