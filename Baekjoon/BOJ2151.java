import java.io.*;
import java.util.*;

public class BOJ2151 {
  enum Stat {Up, Down, Left, Right};
  private static class Pair {
    int row, col, depth;
    Stat stat;
    Pair(int row, int col, int depth) {
      this.row = row;
      this.col = col;
      this.depth = depth;
    }
    Pair(int row, int col, int depth, Stat stat) {
      this(row, col, depth);
      this.stat = stat;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    char[][] house = new char[N][N];
    ArrayList<Pair> door = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      house[i] = br.readLine().toCharArray();
      for (int j = 0; j < N; j++) {
        if (house[i][j] == '#') {
          door.add(new Pair(i, j, 0));
        }
      }
    }

    bw.write(bfs(N, house, door) + "\n");
    bw.flush();
  }
  static int bfs(int N, char[][] house, ArrayList<Pair> door) {
    Pair startDoor = door.get(0);
    Pair endDoor = door.get(1);
    Queue<Pair> que = new LinkedList<>();
    boolean[][][] checked = new boolean[N][N][4];
    // checked[i][j][k]: (i, j)에 k방향으로 온 적 있음
    for (Stat st : Stat.values()) {
      Pair start = new Pair(startDoor.row, startDoor.col, 0, st);
      que.offer(start);
    } 
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      switch (crnt.stat) {
        case Up: {
          for (int i = crnt.row - 1; i >= 0; i--) {
            if (house[i][crnt.col] == '*') break;
            else if (house[i][crnt.col] == '#' && i == endDoor.row && crnt.col == endDoor.col) return crnt.depth;
            else if (house[i][crnt.col] == '!') {
              if (checked[i][crnt.col][0]) break;
              que.offer(new Pair(i, crnt.col, crnt.depth + 1, Stat.Left));
              que.offer(new Pair(i, crnt.col, crnt.depth + 1, Stat.Right));
              checked[i][crnt.col][0] = true;
            }
          }
          break;
        }
        case Down: {
          for (int i = crnt.row + 1; i < N; i++) {
            if (house[i][crnt.col] == '*') break;
            else if (house[i][crnt.col] == '#' && i == endDoor.row && crnt.col == endDoor.col) return crnt.depth;
            else if (house[i][crnt.col] == '!') {
              if (checked[i][crnt.col][1]) break;
              que.offer(new Pair(i, crnt.col, crnt.depth + 1, Stat.Left));
              que.offer(new Pair(i, crnt.col, crnt.depth + 1, Stat.Right));
              checked[i][crnt.col][1] = true;
            }
          }
          break;
        }
        case Left: {
          for (int i = crnt.col - 1; i >= 0; i--) {
            if (house[crnt.row][i] == '*') break;
            else if (house[crnt.row][i] == '#' && crnt.row == endDoor.row && i == endDoor.col) return crnt.depth;
            else if (house[crnt.row][i] == '!') {
              if (checked[crnt.row][i][2]) break;
              que.offer(new Pair(crnt.row, i, crnt.depth + 1, Stat.Up));
              que.offer(new Pair(crnt.row, i, crnt.depth + 1, Stat.Down));
              checked[i][crnt.col][2] = true;
            }
          }
          break;
        }
        default: {
          for (int i = crnt.col + 1; i < N; i++) {
            if (house[crnt.row][i] == '*') break;
            else if (house[crnt.row][i] == '#' && crnt.row == endDoor.row && i == endDoor.col) return crnt.depth;
            else if (house[crnt.row][i] == '!') {
              if (checked[crnt.row][i][3]) break;
              que.offer(new Pair(crnt.row, i, crnt.depth + 1, Stat.Up));
              que.offer(new Pair(crnt.row, i, crnt.depth + 1, Stat.Down));
              checked[i][crnt.col][3] = true;
            }
          }
        }
      }
    }
    return -1;
  }
}
