import java.io.*;

public class BOJ1285 {
  static int N;
  static char[][] board;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    board = new char[N][];
    for (int i = 0; i < N; i++) {
      board[i] = br.readLine().toCharArray();
    }

    bw.write(recur(0, greedy()) + "\n");
    bw.flush();
  }
  static int recur(int depth, int numTail) {
    if (depth >= N) return numTail;
    int result1 = recur(depth + 1, numTail);
    flipRow(depth);
    int result2 = recur(depth + 1, greedy());
    flipRow(depth);
    return Math.min(result1, result2);
  }
  static void flipRow(int idx) {
    for (int i = 0; i < N; i++) {
      board[idx][i] = (board[idx][i] == 'H') ? 'T' : 'H';
    }
  }
  static int greedy() {
    int numTail = 0;
    for (int i = 0; i < N; i++) {
      int numLocal = 0;
      for (int j = 0; j < N; j++) {
        if (board[j][i] == 'T') numLocal++;
      }
      if (numLocal < N - numLocal) {
        numTail += numLocal;
      } else {
        numTail += N - numLocal;
      }
    }
    return numTail;
  }
}
