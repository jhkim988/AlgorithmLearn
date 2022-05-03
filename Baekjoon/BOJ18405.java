import java.io.*;
import java.util.*;

public class BOJ18405 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[][] board = new int[n][n];
    ArrayList<Queue<Pair>> ques = new ArrayList<>();
    for (int i = 0; i <= k; i++) ques.add(new LinkedList<>());
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] == 0) continue;
        ques.get(board[i][j]).add(new Pair(i, j));
      }
    }
    st = new StringTokenizer(br.readLine());
    int s = Integer.parseInt(st.nextToken());
    int targetRow = Integer.parseInt(st.nextToken())-1;
    int targetCol = Integer.parseInt(st.nextToken())-1;
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    Queue<Pair> tmp = new LinkedList<>();
    while (s-- > 0) {
      for (int i = 1; i <= k; i++) {
        while (!ques.get(i).isEmpty()) {
          Pair crnt = ques.get(i).poll();
          for (int d = 0; d < 4; d++) {
            int adjRow = crnt.row + rowDi[d];
            int adjCol = crnt.col + colDi[d];
            if (adjRow < 0 || adjRow >= n || adjCol < 0 || adjCol >= n) continue;
            if (board[adjRow][adjCol] != 0) continue;
            board[adjRow][adjCol] = i;
            tmp.add(new Pair(adjRow, adjCol));
          }
        }
        while (!tmp.isEmpty()) {
          ques.get(i).add(tmp.poll());
        }
      }      
    }
    bw.write(Integer.toString(board[targetRow][targetCol]));
    bw.flush();
  }
}
