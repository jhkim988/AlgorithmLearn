import java.io.*;
import java.util.*;

public class BOJ17837 {
  private static class Piece {
    int row, col, direction;
    Piece(int row, int col, int direction) {
      this.row = row;
      this.col = col;
      this.direction = direction;
    }
  }
  public static void main(String[] args) throws IOException {
    // White: 0, Red: 1, Blue: 2

    int[] rowDi = {0, 0, -1, 1};
    int[] colDi = {1, -1, 0, 0};
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    int[][] board = new int[n][n];
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Piece[] pieces = new Piece[k];
    ArrayList<ArrayList<Stack<Integer>>> pos = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      pos.add(new ArrayList<>());
      for (int j = 0; j < n; j++) {
        pos.get(i).add(new Stack<>());
      }
    }
    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken())-1;
      int col = Integer.parseInt(st.nextToken())-1;
      int direction = Integer.parseInt(st.nextToken())-1;
      pieces[i] = new Piece(row, col, direction);
      pos.get(row).get(col).add(i);      
    }

    int turn = 1;
    while (turn <= 1000) {
      for (int i = 0; i < k; i++) {
        int adjRow = pieces[i].row + rowDi[pieces[i].direction];
        int adjCol = pieces[i].col + colDi[pieces[i].direction];
        boolean reverse = false;
        if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n || board[adjRow][adjCol] == 2) {
          if (pieces[i].direction % 2 == 0) pieces[i].direction++;
          else pieces[i].direction--;
          reverse = true;
        }
        if (reverse) {
          adjRow = pieces[i].row + rowDi[pieces[i].direction];
          adjCol = pieces[i].col + colDi[pieces[i].direction];
          if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n || board[adjRow][adjCol] == 2) continue;
        }
        if (board[adjRow][adjCol] == 0) {
          Stack<Integer> crntPos = pos.get(pieces[i].row).get(pieces[i].col);
          Stack<Integer> tmp = new Stack<>();
          while (crntPos.peek() != i) {
            int pop = crntPos.pop();
            pieces[pop].row = adjRow;
            pieces[pop].col = adjCol;
            tmp.push(pop);
          } 
          int pop = crntPos.pop();
          pieces[pop].row = adjRow;
          pieces[pop].col = adjCol;
          tmp.push(pop);

          Stack<Integer> adjPos = pos.get(adjRow).get(adjCol);
          while (!tmp.isEmpty()) {
            pop = tmp.pop();
            pieces[pop].row = adjRow;
            pieces[pop].col = adjCol;
            adjPos.push(pop);
          }
        } else {
          Stack<Integer> crntPos = pos.get(pieces[i].row).get(pieces[i].col);
          Stack<Integer> adjPos = pos.get(adjRow).get(adjCol);
          while (crntPos.peek() != i) {
            int pop = crntPos.pop();
            pieces[pop].row = adjRow;
            pieces[pop].col = adjCol;
            adjPos.push(pop);
          }
          int pop = crntPos.pop();
          pieces[pop].row = adjRow;
          pieces[pop].col = adjCol;
          adjPos.push(pop);
        }
        pieces[i].row = adjRow;
        pieces[i].col = adjCol;
        if (pos.get(adjRow).get(adjCol).size() >= 4) {
          bw.write(Integer.toString(turn));
          bw.flush();
          return;
        }
      }
      turn++;
    }
    bw.write("-1\n");
    bw.flush();
  }
}