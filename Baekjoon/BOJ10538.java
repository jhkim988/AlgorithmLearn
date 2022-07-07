import java.io.*;
import java.util.*;

public class BOJ10538 {
  static long key = 31;
  static long mod = 1_000_000_7;
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
    for (int i = 0; i < hp; i++) {
      pat[i] = br.readLine().toCharArray();
    }
    char[][] str = new char[hm][wm];
    for (int i = 0; i < hm; i++) {
      str[i] = br.readLine().toCharArray();
    }

    long[] hpat = new long[hp];
    for (int i = 0; i < hp; i++) {
      long exp = 1;
      for (int j = 0; j < wp; j++) {
        hpat[i] += pat[i][j]*exp%mod; hpat[i] %= mod;
        exp = exp*key%mod;
      }
    }

    long[][] hstr = new long[hm][wm-wp+1];
    for (int i = 0; i < hm; i++) {
      long exp = 1;
      for (int j = 0; j < wp; j++) {
        hstr[i][0] += str[i][j]*exp%mod; hstr[i][0] %= mod;
        exp = exp*key%mod;        
      }
    }
    long exp = 1;
    for (int i = 0; i < wp; i++) exp = exp*key%mod;
    for (int i = 0; i < hm; i++) {
      for (int j = 1; j <= wm-wp; j++) {
        long val = hstr[i][j-1];
        val += (mod-str[i][j-1])%mod; val %= mod;
        val += str[i][j-1+wp]*exp; val %= mod;
        hstr[i][j] = val*rev%mod;
      }
    }

    System.out.println(Arrays.toString(hpat));
    for (int i = 0; i < hm; i++) {
      System.out.println(Arrays.toString(hstr[i]));
    }
    bw.write(Integer.toString(0));
    bw.flush();
  }
}
