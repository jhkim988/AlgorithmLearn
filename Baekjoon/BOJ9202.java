import java.io.*;
import java.util.*;

public class BOJ9202 {
  static char[][] board;
  static int[] rowDi = {-1, -1, -1, 0, 0, 1, 1, 1};
  static int[] colDi = {-1, 0, 1, -1, 1, -1, 0, 1};
  static int[] scoreBoard = {0, 0, 0, 1, 1, 2, 3, 5, 11};
  static char[] make = new char[8];
  static boolean[][] visit = new boolean[4][4];
  static HashSet<String> find = new HashSet<>();
  static Trie dict;
  private static class Node {
    // char ch;
    boolean isEnd;
    Node[] child = new Node['Z'-'A'+1];
    boolean[] contains = new boolean['Z'-'A'+1];
  }
  private static class Trie {
    Node head = new Node();
    void add(char[] str) {
      Node ptr = head;
      for (int i = 0; i < str.length; i++) {
        if (!ptr.contains[str[i]-'A']) {
          ptr.child[str[i]-'A'] = new Node();
          ptr.contains[str[i]-'A'] = true;
        } 
        ptr = ptr.child[str[i]-'A'];
      }
      ptr.isEnd = true;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int w = Integer.parseInt(br.readLine());
    dict = new Trie();
    while (w-- > 0) {
      dict.add(br.readLine().toCharArray());
    }
    br.readLine();
    int b = Integer.parseInt(br.readLine());
    board = new char[4][];
    while (b-- > 0) {
      for (int i = 0; i < 4; i++) {
        board[i] = br.readLine().toCharArray();
      }
      br.readLine();
      find.clear();
      for (int i = 0; i < 4; i++) Arrays.fill(visit[i], false);
      Arrays.fill(make, (char) 0);
      String longestWord = "";
      int score = 0;
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          if (!dict.head.contains[board[i][j]-'A']) continue;
          visit[i][j] = true;
          make[0] = board[i][j];
          dfs(0, i, j, dict.head.child[board[i][j] - 'A'], make);
          visit[i][j] = false;
        }
      }
      for (String str : find) {
        score += scoreBoard[str.length()];
        if (longestWord.length() < str.length()) longestWord = str;
        else if (longestWord.length() == str.length()) {
          if (longestWord.compareTo(str) > 0) longestWord = str;
        }
      }
      bw.write(Integer.toString(score));
      bw.write(' ');
      bw.write(longestWord);
      bw.write(' ');
      bw.write(Integer.toString(find.size()));
      bw.newLine();
    }
    bw.flush();
  }
  static void dfs(int depth, int row, int col, Node ptr, char[] make) {
    if (ptr.isEnd) find.add(new String(make, 0, depth+1));
    if (depth >= 8) return;
    for (int k = 0; k < 8; k++) {
      int adjRow = row + rowDi[k];
      int adjCol = col + colDi[k];
      if (adjRow < 0 || adjRow >= 4 || adjCol < 0 || adjCol >= 4) continue;
      if (visit[adjRow][adjCol]) continue;
      if (!ptr.contains[board[adjRow][adjCol]-'A']) continue;
      Node next = ptr.child[board[adjRow][adjCol]-'A'];
      visit[adjRow][adjCol] = true;
      make[depth+1] = board[adjRow][adjCol];
      dfs(depth+1, adjRow, adjCol, next, make);
      visit[adjRow][adjCol] = false;
    }
  }
}
