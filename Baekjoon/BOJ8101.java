import java.io.*;
import java.util.*;

public class BOJ8101 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    while (n-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int l = a + Integer.parseInt(st.nextToken());
      
      double lo = 0;
      double hi = Math.PI/2;
      while (hi - lo > 1e-15) {
        double mid = (lo + hi) / 2;
        if (a*mid/l < Math.sin(mid)) {
          lo = mid;
        } else {
          hi = mid;
        }
      }
      double th = hi;
      double r = l / (2*th);
      bw.write(Integer.toString((int) Math.round(r * (1 - Math.cos(th)))));
      bw.newLine();
    }
    bw.flush();
  }
}
