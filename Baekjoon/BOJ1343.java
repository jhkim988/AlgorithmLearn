import java.io.*;

public class BOJ1343 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] board = br.readLine().toCharArray();
    for (int i = 0; i < board.length; i++) {
      if (board[i] != 'X') continue;
      int num = 0;
      int j;
      for (j = i; j < board.length; j++) {
        if (board[j] != 'X') break;
        num++;
        if (num == 4) {
          for (int k = 0; k < 4; k++) {
            board[j-k] = 'A';
          }
          num = 0;
        }
      }
      if (num % 2 == 1) {
        bw.write("-1\n");
        bw.flush();
        return;
      } else if (num > 0) {
        for (int k = 1; k <= 2; k++) {
          board[j-k] = 'B';
        }
      }
      i = j;
    }
    bw.write(board);
    bw.flush();
  }
}
