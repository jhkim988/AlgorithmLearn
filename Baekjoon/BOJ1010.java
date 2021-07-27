import java.io.*;

public class BOJ1010 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    int[][] table = new int[30][30];
    table[1][0] = 1;
    table[1][1] = 1;
    String[] tmp;
    for (int i = 0; i < numTest; i++) {
      tmp = br.readLine().split(" ");
      bw.write(comb(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[0]), table) + "\n");
    }
    bw.flush();
  }
  static int comb(int N, int K, int[][] table) {
    if (table[N][K] == 0) {
      for (int i = 2; i <= N; i++) {
        table[i][0] = 1;
        table[i][i] = 1;
        for (int j = 1; j < i; j++) {
          table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]);
        }
      }
    }
    return table[N][K];
  }
}
