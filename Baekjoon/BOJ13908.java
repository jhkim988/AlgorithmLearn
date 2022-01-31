import java.io.*;
import java.util.*;

public class BOJ13908 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    br.readLine();
    int answer = power(10, n) - recur(n, m, 1, 1);
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int recur(int n, int m, int depth, int count) {
    if (depth > m) {
      return 0;
    }
    int ret = power(10 - count, n);
    ret -= recur(n, m, depth + 1, count + 1);
    ret += recur(n, m, depth + 1, count);
    return ret;
  }
  static int power(int base, int power) {
    if (power == 0) return 0;
    if (power == 1) return base;
    int half = power(base, power/2);
    if (power % 2 == 0) return half * half;
    else return half*half*base;
  }
}
