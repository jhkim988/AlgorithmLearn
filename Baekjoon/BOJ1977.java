import java.io.*;

public class BOJ1977 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int lo = Integer.parseInt(br.readLine());
    int hi = Integer.parseInt(br.readLine());
    int sq1 = upperSqrt(lo);
    int sq2 = lowerSqrt(hi);
    if (sq1 > sq2) {
      bw.write("-1\n");
      bw.flush();
      return;
    }
    int sum = 0;
    for (int i = sq1; i <= sq2; i++) {
      sum += i*i;
    }
    bw.write(Integer.toString(sum));
    bw.newLine();
    bw.write(Integer.toString(sq1*sq1));
    bw.newLine();
    bw.flush();
  }
  static int upperSqrt(int x) {
    int lo = 0, hi = x;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (mid*mid < x) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  static int lowerSqrt(int x) {
    int lo = 0, hi = x;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (mid*mid <= x) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
}