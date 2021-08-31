import java.io.*;

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

    long[][] dp = new long[1 << N][K];
    int[] numData = new int[N];
    for (int i = 0; i < N; i++) {
      int len = data[i].length();
      for (int j = 0; j < len; j++) {
        numData[i] = ((data[i].charAt(j) - '0') + (numData[i] * 10) % K) % K;
      }
    }
    dp[0][0] = 1;
    for (int i = 0; i < (1<<N); i++) {
      for (int j = 0; j < K; j++) {
        for (int t = 0; t < N; t++) {
          if ((i & (1 << t)) != 0) continue;
          dp[i | (1 << t)][(numData[t] + (j * pow(10, data[t].length(), K)) % K) % K] += dp[i][j];
        }
      }
    }

    long p = dp[(1<<N) - 1][0];
    long q = 1;
    for (int i = 2; i <= N; i++) {
      q *= i;
    }
    long t = gcd(p, q);
    p /= t;
    q /= t;
    bw.write(p + "/" + q + "\n");
    bw.flush();
  }
  static long gcd (long a, long b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
  static int pow(int base, int exp, int remainder) {
    if (exp == 0) {
      return 1;
    } else if (exp == 1) {
      return base % remainder;
    } else {
      int prev = pow(base, exp / 2, remainder);
      if (exp % 2 == 0) {
        return (prev * prev) % remainder;
      } else {
        return (((prev * prev) % remainder) * base) % remainder;
      }
    }
  }
}
