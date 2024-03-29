import java.io.*;
import java.util.*;

public class BOJ2239 {
  static boolean find = false;
  private static class Pair {
    int i;
    int j;
    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[][] board = new char[9][9];
    ArrayList<Pair> empty = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      board[i] = br.readLine().toCharArray();
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '0') {
          empty.add(new Pair(i, j));
        }
      }
    } 
    backtracking(board, empty, 0);
  }
  static void print(char[][] board) throws IOException {    
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for (int i = 0; i < 9; i++) {
      bw.write(String.copyValueOf(board[i]) + "\n");
    }
    bw.flush();
  }
  static void backtracking(char[][] board, ArrayList<Pair> empty, int depth) throws IOException {
    if (find) {
      return;
    }
    if (depth == empty.size()) {
      print(board);
      find = true;
      return;
    }     
    Pair crnt = empty.get(depth);
    for (char next : candidate(crnt, board)) {
      board[crnt.i][crnt.j] = next;
      backtracking(board, empty, depth + 1);
      board[crnt.i][crnt.j] = '0';
    }
  }
  static ArrayList<Character> candidate(Pair crnt, char[][] board) {
    boolean[] check = new boolean[10];
    for (int i = 0; i < 10; i++) {
      check[i] = true;
    }
    for (int i = 0; i < 9; i++) {
      if (board[crnt.i][i] != '0') {
        check[board[crnt.i][i] - '0'] = false;
      }
      if (board[i][crnt.j] != '0') {
        check[board[i][crnt.j] - '0'] = false;
      }
    }

    int startI = crnt.i / 3 * 3;
    int startJ = crnt.j / 3 * 3;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int nextI = startI + i;
        int nextJ = startJ + j;
        if (board[nextI][nextJ] != '0') {
          check[board[nextI][nextJ] - '0'] = false;
        }
      }
    }

    ArrayList<Character> result = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      if (!check[i]) continue;
      result.add((char)(i + '0'));
    }
    return result;
  }
}
