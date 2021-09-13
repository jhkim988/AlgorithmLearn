import java.io.*;
import java.util.*;

public class BOJ3055 {
  static int[] xDi = {0, 1, 0, -1};
  static int[] yDi = {1, 0, -1, 0};
  private static class Node {
    int row;
    int col;
    int numMove;
    Node(int row, int col, int numMove) {
      this.row = row;
      this.col = col;
      this.numMove = numMove;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    char[][] map = new char[row][col];
    Node D = null;
    Node S = null;
    ArrayList<Node> water = new ArrayList<>();
    for (int i = 0; i < row; i++) {
      String tmp = br.readLine();
      map[i] = tmp.toCharArray();
      for (int j = 0; j < col; j++) {
        if (map[i][j] == 'D') {
          map[i][j] = '.';
          D = new Node(i, j, 0);
        }
        if (map[i][j] == 'S') {
          map[i][j] = '.';
          S = new Node(i, j, 0);
        }
        if (map[i][j] == '*') {
          water.add(new Node(i, j, 0));
        }
      }
    }

    // BFS:
    int status = 0; // numMove
    boolean[][] marked = new boolean[row][col];
    Queue<Node> que = new LinkedList<>();
    que.add(S);
    marked[S.row][S.col] = true;
    map = flood(map, water, row, col, D);
    while (!que.isEmpty()) {
      Node crnt = que.poll();
      // print(map, marked, crnt.numMove, D, crnt, row, col);
      if (crnt.row == D.row && crnt.col == D.col) {
        bw.write(crnt.numMove + "\n");
        bw.flush();
        return;
      }

      if (status < crnt.numMove) {
        status = crnt.numMove;
        map = flood(map, water, row, col, D);
      }

      for (int i = 0; i < 4; i++) {
        int nextRow = crnt.row + xDi[i];
        int nextCol = crnt.col + yDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (marked[nextRow][nextCol]) continue;
        if (map[nextRow][nextCol] == 'X' || map[nextRow][nextCol] == '*') continue;
        que.add(new Node(nextRow, nextCol, crnt.numMove + 1));
        marked[nextRow][nextCol] = true;
      }
    }

    bw.write("KAKTUS\n");
    bw.flush();
  } 
  static char[][] flood(char[][] map, ArrayList<Node> water, int row, int col, Node D) {
    boolean[][] marked = new boolean[row][col];
    Queue<Node> tmp = new LinkedList<>();
    for (Node n : water) {
      for (int i = 0; i < 4; i++) {
        int nextRow = n.row + xDi[i];
        int nextCol = n.col + yDi[i];
        if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) continue;
        if (marked[nextRow][nextCol]) continue;
        if (map[nextRow][nextCol] == 'X' || map[nextRow][nextCol] == '*' || (D.row == nextRow && D.col == nextCol)) continue;
        map[nextRow][nextCol] = '*';
        marked[nextRow][nextCol] = true;
        tmp.add(new Node(nextRow, nextCol, 0));
      }
    }
    while (!tmp.isEmpty()) {
      water.add(tmp.poll());
    }
    return map;
  } 
  static void print(char[][] map, boolean[][] marked, int numMove, Node D, Node S, int row, int col) {
    StringBuilder sb = new StringBuilder();
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        if (D.row == r && D.col == c) {
          sb.append('D');
        } else if (S.row == r && S.col == c) {
          sb.append('S');
        } else if (marked[r][c]) {
          sb.append('O');
        } else {
          sb.append(map[r][c]);
        }
      }
      sb.append('\n');
    }
    System.out.println("------------------------");
    System.out.print(sb.toString());
    System.out.println("numMove: " + numMove);
  }
}
