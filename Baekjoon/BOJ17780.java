import java.io.*;
import java.util.*;

public class BOJ17780 {
  static int size;
  static int[] rowDi = {0, 0, -1, 1};
  static int[] colDi = {1, -1, 0, 0};
  static int[][] board;
  static int[][] pos;
  static HashMap<Integer, MovingPiece> hm;
  private static class Pair {
    int id, direction;
    Pair(int id, int direction) {
      this.id = id;
      this.direction = direction;
    }
  }
  private static class MovingPiece {
    int row, col, id;
    Deque<Pair> deq;
    boolean isLastBottom = true;
    MovingPiece(int row, int col, int id, int direction) {
      this.row = row;
      this.col = col;
      this.id = id;
      deq = new LinkedList<>();
      deq.add(new Pair(id, direction));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    size = Integer.parseInt(st.nextToken());
    int numPiece = Integer.parseInt(st.nextToken());
    board = new int[size][size];
    for (int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    pos = new int[size][size];
    hm = new HashMap<>(); // id, info
    for (int i = 1; i <= numPiece; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken()) - 1;
      int col = Integer.parseInt(st.nextToken()) - 1;
      int direction = Integer.parseInt(st.nextToken()) - 1;
      hm.put(i, new MovingPiece(row, col, i, direction));
      pos[row][col] = i;
    }

    int numTurn = 0;
    turn: while (true) {
      numTurn++;
      if (numTurn > 1000) {
        bw.write("-1\n");
        bw.flush();
        return;
      }
      for (int i = 1; i <= numPiece; i++) {
        if (!hm.containsKey(i)) continue;
        MovingPiece mp = hm.get(i);
        int direction = mp.isLastBottom ? mp.deq.peekLast().direction : mp.deq.peekFirst().direction;
        int nextRow = mp.row + rowDi[direction];
        int nextCol = mp.col + colDi[direction];
        if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size || board[nextRow][nextCol] == 2) {
          moveBlue(mp);
        } else if (board[nextRow][nextCol] == 1) {
          moveRed(mp, nextRow, nextCol);
        } else {
          moveWhite(mp, nextRow, nextCol);
        }
      }
      for (MovingPiece mp : hm.values()) if (mp.deq.size() >= 4) break turn;
    }
    bw.write(numTurn + "\n");
    bw.flush();
  }
  static void moveBlue(MovingPiece mp) {
    Pair p = mp.isLastBottom ? mp.deq.peekLast() : mp.deq.peekFirst();
    switch (p.direction) {
      case 0:
        p.direction = 1;
        break;
      case 1:
        p.direction = 0;
        break;
      case 2:
        p.direction = 3;
        break;
      default:
        p.direction = 2;
        break;
    }
    int nextRow = mp.row + rowDi[p.direction];
    int nextCol = mp.col + colDi[p.direction];
    if (nextRow < 0 || nextRow >= size || nextCol < 0 ||  nextCol >= size) return;
    switch (board[nextRow][nextCol]) {
      case 0:
        moveWhite(mp, nextRow, nextCol);
        return;
      case 1:
        moveRed(mp, nextRow, nextCol);
        return;
      default:
        return;
    }
  }
  static void moveRed(MovingPiece mp, int nextRow, int nextCol) {
    mp.isLastBottom = !mp.isLastBottom;
    hm.remove(mp.id);
    Pair p = mp.isLastBottom ? mp.deq.peekLast() : mp.deq.peekFirst();
    mp.id = p.id;
    hm.put(mp.id, mp);
    moveWhite(mp, nextRow, nextCol);
  }
  static void moveWhite(MovingPiece mp, int nextRow, int nextCol) {
    if (pos[nextRow][nextCol] == 0) {
      pos[mp.row][mp.col] = 0;
      pos[nextRow][nextCol] = mp.id;
      mp.row = nextRow;
      mp.col = nextCol;
    } else {
      MovingPiece other = hm.get(pos[nextRow][nextCol]);
      if (mp.isLastBottom) {
        if (other.isLastBottom) while (!mp.deq.isEmpty()) {other.deq.addFirst(mp.deq.removeLast());}
        else while (!mp.deq.isEmpty()) {other.deq.addLast(mp.deq.removeLast());}
      } else {
        if (other.isLastBottom) while (!mp.deq.isEmpty()) {other.deq.addFirst(mp.deq.removeFirst());}
        else while (!mp.deq.isEmpty()) {other.deq.addLast(mp.deq.removeFirst());}
      }
      pos[mp.row][mp.col] = 0;
      hm.remove(mp.id);
    }
  }
}