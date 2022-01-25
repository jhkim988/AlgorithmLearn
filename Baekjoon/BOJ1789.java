import java.io.*;

public class BOJ1789 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long S = Long.parseLong(br.readLine());

    long lo = 0;
    long hi = S;
    while (lo + 1 < hi) {
      long mid = (lo + hi) / 2;
      if (check(mid, S)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    bw.write(Long.toString(lo));
    bw.newLine();
    bw.flush();
  }
  static boolean check(long val, long S) {
    if (val <= 0) return true;
    return (val - 1) * (val + 2) / 2 < S;
  }
}
