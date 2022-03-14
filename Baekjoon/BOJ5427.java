import java.io.*;
import java.util.*;

public class BOJ5427 {
  private static int row, col;
  private static final int MAX = 1_000;
  private static final int INF = 5000;
  private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
  private static char[][] map = new char[MAX][];
  private static int[][] move = new int[MAX][MAX];
  private static Queue<Integer> fireQue = new LinkedList<>();
  private static Queue<Integer> moveQue = new LinkedList<>();
  static void fire() {
    int size = fireQue.size();
    for (int i = 0; i < size; i++) {
      int crnt = fireQue.poll();
      for (int k = 0; k < 4; k++) {
        int adjRow = crnt/col + rowDi[k];
        int adjCol = crnt%col + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
        if (map[adjRow][adjCol] == '#' || map[adjRow][adjCol] == '*') continue;
        map[adjRow][adjCol] = '*';
        fireQue.add(adjRow * col + adjCol);
      }
    }
  }
  static int simulation() {
    int time = -1;
    while (!moveQue.isEmpty()) {
      int crnt = moveQue.poll();
      int crntRow = crnt/col;
      int crntCol = crnt%col;
      if (time < move[crntRow][crntCol]) {
        time = move[crntRow][crntCol];
        fire();
      }
      for (int k = 0; k < 4; k++) {
        int adjRow = crntRow + rowDi[k];
        int adjCol = crntCol + colDi[k];
        if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) {
          return move[crntRow][crntCol] + 1;
        }
        if (map[adjRow][adjCol] != '.') continue;
        if (move[adjRow][adjCol] != INF) continue;
        move[adjRow][adjCol] = move[crntRow][crntCol] + 1;
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
          move[i][j] = INF;
        }
      }
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          if (map[i][j] == '@') {
            moveQue.add(i * col + j);
            move[i][j] = 0;
          } else if (map[i][j] == '*') {
            fireQue.add(i * col + j);
          }
        }
      }
      int answer = simulation();
      bw.write(answer >= 0 ? Integer.toString(answer) : "IMPOSSIBLE");
      bw.newLine();
      fireQue.clear(); moveQue.clear();
    }
    bw.flush();
  }
}
