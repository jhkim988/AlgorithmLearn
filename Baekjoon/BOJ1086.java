import java.io.*;
import java.util.*;

public class BOJ1086 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    String[] data = new String[N];
    for (int i = 0; i < N; i++) {
      data[i] = br.readLine();
    }
    int K = Integer.parseInt(br.readLine());

    long[][] dp = new long[N + 1][1 << N];
    // for (int i = 0; i < N; i++) {
    //   dp[1][1 << i] = data[i] % K;
    // }

    // for (int i = 2; i <= N; i++) {
    //   for (int j = 0; j < N; j++) {
    //     dp[i][bit | 1 << j] = ((dp[i - 1][bit] * pow(10, data[j].length())) % K + dp[1][1 << j]) % K;
    //   }
    // }
  }
}
