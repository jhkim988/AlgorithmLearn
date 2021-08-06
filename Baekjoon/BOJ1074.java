import java.io.*;
import java.util.*;

public class BOJ1074 {
  static int[] table;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    table = new int[16];
    table[0] = 1;
    for (int i = 1; i < 16; i++) {
      table[i] = 2 * table[i - 1];
    }

    int result = order(N, r, c, 0);
    bw.write(result + "\n");
    bw.flush();
  }
  static int order(int N, int r, int c, int result) {
    if (N == 1) {
      int[][] sample = new int[][] {{0, 1}, {2, 3}};
      return sample[r][c] + result;
    }
    int sum = table[N - 1];
    if (r < table[N - 1]) {
      if (c < table[N - 1]) {
        return order(N - 1, r, c, result);
      } else {
        return order(N - 1, r, c - sum, result + sum * sum);
      }
    } else {
      if (c < table[N - 1]) {
        return order(N - 1, r - sum, c, result + 2 * sum * sum);
      } else {
        return order(N - 1, r - sum, c - sum, result + 3 * sum * sum);
      }
    }
  }
}
