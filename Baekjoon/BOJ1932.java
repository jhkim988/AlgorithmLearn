import java.io.*;

public class BOJ1932 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int sizeTri = Integer.parseInt(br.readLine());
      int[][] memo = new int[sizeTri][1];
      String[] tmp;
      int input;
      for (int i = 0; i < sizeTri; i++) {
        memo[i] = new int[i + 1];
        tmp = br.readLine().split(" ");
        if (i > 0) {
          for (int j = 0; j <= i; j++) {
            input = Integer.parseInt(tmp[j]);
            if (j > 0 && j < i) {
              memo[i][j] = memo[i - 1][j - 1] > memo[i - 1][j]
              ? input + memo[i - 1][j - 1] : input + memo[i - 1][j];
            } else if (j == 0) {
              memo[i][j] = input + memo[i - 1][j];
            } else {
              memo[i][j] = input + memo[i - 1][j - 1];
            }
          }
        } else {
          memo[0][0] = Integer.parseInt(tmp[0]);
        }
      }

      int max = memo[sizeTri - 1][0];
      for (int el : memo[sizeTri - 1]) {
        if (max < el) {
          max = el;
        }
      }
      bw.write(max + "\n");
      bw.flush();
      bw.close();
      br.close();
    } catch (IOException e) {
      // do nothing
    }
  }
}
