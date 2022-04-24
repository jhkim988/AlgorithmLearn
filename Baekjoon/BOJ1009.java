import java.io.*;
import java.util.*;

public class BOJ1009 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int p = pow(a, b, 10);
      bw.write(Integer.toString(p == 0 ? 10 : p));
      bw.newLine();
    }
    bw.flush();
  }
  static int pow(int base, int exp, int mod) {
    if (exp == 0) return 1;
    if (exp == 1) return base % mod;
    int half = pow(base, exp/2, mod);
    if (exp % 2 == 0) return (half * half) % mod;
    else return (((half * half) % mod) * base) % mod;
  }
}
