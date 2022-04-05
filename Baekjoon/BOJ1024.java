import java.io.*;
import java.util.*;

public class BOJ1024 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long t = 2 * Long.parseLong(st.nextToken());
    long l = Long.parseLong(st.nextToken());
    for (; l <= Long.min(t, 100); l++) {
      if (t % l != 0) continue;
      long val = t/l - l + 1;
      if (val < 0) break;
      if (val % 2 == 0) {
        long start = val / 2;
        for (int i = 0; i < l; i++) {
          bw.write(Long.toString(start + i));
          bw.write(' ');
        }
        bw.flush();
        return;
      }
    }
    bw.write("-1\n");
    bw.flush();
  }
}
