import java.io.*;

public class BOJ14238 {
  static int len, numA, numB, numC;
  static boolean[][][][][] dp;
  static char[] answer;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    len = input.length;
    numA = numB = numC = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i] == 'A') numA++;
      else if (input[i] == 'B') numB++;
      else numC++;
    }

    dp = new boolean[numA + 1][numB + 1][numC + 1][3][3];
    answer = new char[len];

    bw.write(recur(0, 0, 0, 0, 0) ? new String(answer) : "-1");
    bw.newLine();
    bw.flush();
  }
  static boolean recur(int a, int b, int c, int past, int ppast) {
    if (a == numA && b == numB && c == numC) return true;
    if (dp[a][b][c][past][ppast]) return false; // ?
    dp[a][b][c][past][ppast] = true;

    answer[a + b + c] = 'A';
    if (a < numA && recur(a + 1, b, c, 0, past)) return true;

    answer[a + b + c] = 'B';
    if (b < numB && past != 1 && recur(a, b + 1, c, 1, past)) return true;

    answer[a + b + c] = 'C';
    if (c < numC && past != 2 && ppast != 2 && recur(a, b, c + 1, 2, past)) return true;

    return false;
  }
}
