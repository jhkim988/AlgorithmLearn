import java.io.*;
import java.util.*;

public class BOJ13172 {
  static final int MOD = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int M = Integer.parseInt(br.readLine());
    StringTokenizer st;
    long sum = 0;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      Long Ni = Long.parseLong(st.nextToken());
      Long Si = Long.parseLong(st.nextToken());
      sum = (sum + (Si * pow(Ni, MOD - 2)) % MOD) % MOD;
    }
    bw.write(sum + "\n");
    bw.flush();
  }
  static long pow(long base, int power) {
    if (power == 0) {
      return 1L;
    }
    if (power == 1) {
      return base;
    }
    long half = pow(base, power / 2);
    if (power % 2 == 0) {      
      return (half * half) % MOD;
    } else {
      return (((half * half) % MOD) * base) % MOD;
    }
  }
}
