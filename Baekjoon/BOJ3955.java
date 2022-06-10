import java.io.*;
import java.util.*;

public class BOJ3955 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {

    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      ext_Euclidean(a, b);
    }
    bw.flush();
  }
  static void ext_Euclidean(long a, long b) throws IOException {
    long oldr = a, olds = 1, oldt = 0;
    long r = b, s = 0, t = 1;
    long tmp = 0;
    while (r != 0) {
      long q = oldr/r;
      tmp = r;
      r = oldr-q*r;
      oldr = tmp;

      tmp = s;
      s = olds-q*s;
      olds = tmp;

      tmp = t;
      t = oldt-q*t;
      oldt = tmp;
    }
    while (olds >= 0 || oldt <= 0) {
      oldt += a;
      olds -= b;
    }
    while (oldt > 1_000_000_00 && olds > 0) {
      oldt -= a;
      olds += b;
    }
    if (oldr == 1 && oldt <= 1_000_000_000) {
      bw.write(Long.toString(oldt));
    } else {
      bw.write("IMPOSSIBLE");
    }
    bw.newLine();
  }
}
