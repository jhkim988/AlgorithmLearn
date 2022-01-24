import java.io.*;

public class BOJ2417 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());

    long lo = 0;
    long hi = n;
    while (lo + 1 < hi) {
      long mid = (lo + hi) / 2;
      if (mid < n / mid + (n % mid == 0 ? 0 : 1)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    bw.write(Long.toString(hi));
    bw.newLine();
    bw.flush();
  }  
}
