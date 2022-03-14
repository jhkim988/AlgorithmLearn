import java.io.*;
import java.util.*;

public class BOJ5427 {
  private static int row, col;
  private static final int MAX = 1_000;
  private static final int INF = 5000;
  private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static char[][] map = new char[MAX][];
  private static int[][] fire = new int[MAX][MAX], move = new int[MAX][MAX];
  private static Queue<Integer> fireQue = new LinkedList<>();
  private static Queue<Integer> moveQue = new LinkedList<>();
  static void bfsFire() {
    while (!fireQue.isEmpty()) {
      int crnt = fireQue.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt/col + rowDi[k];
        int adjCol = crnt%col + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (map[adjRow][adjCol] == '#') continue;
        if (fire[adjRow][adjCol] < INF) continue;
        fire[adjRow][adjCol] = fire[crnt/col][crnt%col] + 1;
        fireQue.add(adjRow * col + adjCol);
      }
    }
  }
  static int simulation() {
    while (!moveQue.isEmpty()) {
      int crnt = moveQue.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt/col + rowDi[k];
        int adjCol = crnt%col + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) {
          return move[crnt/col][crnt%col] + 1;
        }
        if (map[adjRow][adjCol] != '.') continue;
        if (move[adjRow][adjCol] < INF) continue;
        if (fire[adjRow][adjCol] <= move[crnt/col][crnt%col] + 1) continue;
        move[adjRow][adjCol] = move[crnt/col][crnt%col] + 1;
        moveQue.add(adjRow * col + adjCol);
      }
    }
    return -1;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      col = Integer.parseInt(st.nextToken());
      row = Integer.parseInt(st.nextToken());
      for (int i = 0; i < row; i++) {
        map[i] = br.readLine().toCharArray();
      }
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          move[i][j] = fire[i][j] = INF;
        }
      }
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (map[i][j] == '@') {
            moveQue.add(i * col + j);
            move[i][j] = 0;
          } else if (map[i][j] == '*') {
            fireQue.add(i * col + j);
            fire[i][j] = 0;
          }
        }
      }
      bfsFire();
      int answer = simulation();
      bw.write(answer >= 0 ? Integer.toString(answer) : "IMPOSSIBLE");
      bw.newLine();
      fireQue.clear(); moveQue.clear();
    }
    bw.flush();
  }
}
