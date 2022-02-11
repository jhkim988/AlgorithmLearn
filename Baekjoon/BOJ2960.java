import java.io.*;
import java.util.*;

public class BOJ2960 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    bw.write(Integer.toString(answer(n, k)));
    bw.newLine();
    bw.flush();
  }
  static int answer(int n, int k) {
    boolean[] notPrime = new boolean[n+1];
    int num = 0;
    for (int i = 2; i < n+1; i++) {
      if (notPrime[i]) continue;
      num++;
      if (num == k) return i;
      for (int j = i+i; j <= n; j += i) {
        if (notPrime[j]) continue;
        notPrime[j] = true;
        num++;
        if (num == k) return j;
      }
    }
    return -1;
  }
}
