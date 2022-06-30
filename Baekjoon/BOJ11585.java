import java.io.*;
import java.util.*;

public class BOJ11585 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    char[] str = new char[2*n];
    for (int i = 0; i < n; i++) {
      str[i] = str[i+n] = st.nextToken().charAt(0);
    }
    st = new StringTokenizer(br.readLine());
    char[] pat = new char[n];
    for (int i = 0; i < n; i++) {
      pat[i] = st.nextToken().charAt(0);
    }

    int num = 0;
    int[] pi = pi(pat);
    int j = 0;
    for (int i = 0; i < str.length-1; i++) {
      while (j > 0 && str[i] != pat[j]) j = pi[j-1];
      if (str[i] == pat[j]) {
        if (j == n-1) {
          num++;
          j = pi[j];
        } else {
          j++;
        }
      }
    }
    int gcd = gcd(num, n);
    bw.write(Integer.toString(num/gcd));
    bw.write('/');
    bw.write(Integer.toString(n/gcd));
    bw.flush();
  }
  static int[] pi(char[] pat) {
    int[] pi = new int[pat.length];
    int j = 0;
    for (int i = 1; i < pat.length; i++) {
      while (j > 0 && pat[i] != pat[j]) j = pi[j-1];
      if (pat[i] == pat[j]) pi[i] = ++j;
    }
    return pi;
  }
  static int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a%b);
  }
}
