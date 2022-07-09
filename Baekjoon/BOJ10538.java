import java.io.*;
import java.util.*;

public class BOJ10538 {
  static long key = 31;
  static long mod = 1_000_000_007;
  static long rev = 129_032_259;
  static int hp, wp, hm, wm;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    hp = Integer.parseInt(st.nextToken());
    wp = Integer.parseInt(st.nextToken());
    hm = Integer.parseInt(st.nextToken());
    wm = Integer.parseInt(st.nextToken());

    char[][] pat = new char[hp][wp];
    char[][] str = new char[hm][wm];
    for (int i = 0; i < hp; i++) pat[i] = br.readLine().toCharArray();
    for (int i = 0; i < hm; i++) str[i] = br.readLine().toCharArray();

    long[] patHash = patHash(pat);
    long[][] strHash = strHash(str);
    int[] pi = pi(patHash);

    int num = 0;
    for (int i = 0; i <= hm-hp; i++) {
      int jj = 0;
      for (int j = 0; j < wm; j++) {
        while (jj > 0 && strHash[i][j] != patHash[jj]) jj = pi[jj-1];
        if (strHash[i][j] == patHash[jj]) {
          if (jj == wp-1) {
            jj = pi[jj];
            num++;
          } else {
            jj++;
          }
        }
      }
    }

    bw.write(Integer.toString(num));
    bw.flush();
  }
  static long[] patHash(char[][] pat) {
    long[] ret = new long[pat[0].length];
    long exp = 1;
    for (int i = 0; i < pat.length; i++) {
      for (int j = 0; j < ret.length; j++) {
        ret[j] += pat[i][j]*exp%mod; ret[j] %= mod;
      }
      exp = exp*key%mod;
    }
    return ret;
  }
  static long[][] strHash(char[][] str) {
    long[][] ret = new long[hm-hp+1][wm];
    long exp = 1;
    for (int i = 0; i < hp; i++) {
      for (int j = 0; j < str[0].length; j++) {
        ret[0][j] += str[i][j]*exp%mod; ret[0][j] %= mod;
      }
      exp = exp*key%mod;
    }
    for (int i = 1; i <= hm-hp; i++) {
      for (int j = 0; j < str[0].length; j++) {
        long val = ret[i-1][j];
        val += (mod-str[i-1][j])%mod; val %= mod;
        val += str[i-1+hp][j]*exp%mod; val %= mod;
        ret[i][j] = val*rev%mod;
      }
    }
    return ret;
  }
  static int[] pi(long[] patHash) {
    int[] pi = new int[patHash.length];
    int j = 0;
    for (int i = 1; i < patHash.length; i++) {
      while (j > 0 && patHash[i] != patHash[j]) j = pi[j-1];
      if (patHash[i] == patHash[j]) pi[i] = ++j;
    }
    return pi;
  }
  static long pow(long base, long exp) {
    if (exp == 0) return 1;
    if (exp == 1) return base%mod;
    long half = pow(base, exp/2);
    if (exp % 2 == 0) return half*half%mod;
    return half*half%mod*base%mod;
  }
}
