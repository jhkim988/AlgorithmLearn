import java.io.*;

public class BOJ11051 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] tmp = br.readLine().split(" ");
    int N = Integer.parseInt(tmp[0]);
    int K = Integer.parseInt(tmp[1]);
    
    int[][] table = new int[N + 1][N + 1];
    table[1][0] = 1;
    table[1][1] = 1;

    for (int i = 2; i <= N; i++) {
      table[i][0] = 1;
      table[i][i] = 1;
      for (int j = 1; j < i; j++) {
        table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % 10_007;
      }
    }
    bw.write(table[N][K] + "\n");
    bw.flush();
  } 
}
