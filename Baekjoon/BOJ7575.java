import java.io.*;
import java.util.*;

public class BOJ7575 {
  static int k;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    int[][] str = new int[n][];
    for (int i = 0; i < n; i++) {
      int len = Integer.parseInt(br.readLine());
      if (len < k) {
        bw.write("NO\n");
        bw.flush();
        return;
      }
      str[i] = new int[len];
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < len; j++) {
        str[i][j] = Integer.parseInt(st.nextToken());
      }
    } 

    nextPattern: for (int lo = 0; lo+k-1 < str[0].length; lo++) {
      int hi = lo+k-1;
      int[] pi = pi(str[0], lo, hi);
      int[] rev = pi(str[0], hi, lo);
      for (int i = 1; i < n; i++) {
        if (!kmp(str[i], str[0], lo, hi, pi) && !kmp(str[i], str[0], hi, lo, rev)) continue nextPattern;
      }
      bw.write("YES\n");
      bw.flush();
      return;
    }
    bw.write("NO\n");
    bw.flush();
  }
  static int[] pi(int[] str, int start, int end) {
    int add = start < end ? 1 : -1;
    int[] pi = new int[k];
    int j = 0;
    for (int i = 1; i < k; i++) {
      while (j > 0 && str[start+add*i] != str[start+add*j]) j = pi[j-1];
      if (str[start+add*i] == str[start+add*j]) pi[i] = ++j;
    }
    return pi;
  }
  static boolean kmp(int[] str, int[] pat, int start, int end, int[] pi) {
    int add = start < end ? 1 : -1;
    int j = 0;
    for (int i = 0; i < str.length; i++) {
      while (j > 0 && str[i] != pat[start+add*j]) j = pi[j-1];
      if (str[i] == pat[start+add*j]) {
        if (j == k-1) return true;
        else j++;
      }
    }
    return false;
  }
}
