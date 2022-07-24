import java.io.*;
import java.util.*;

public class BOJ14490 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine(), ":");
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int g = gcd(a, b);
    bw.write(Integer.toString(a/g));
    bw.write(':');
    bw.write(Integer.toString(b/g));
    bw.flush();
  }
  static int gcd(int a, int b) {
    int r = a%b;
    while (r != 0) {
      a = b;
      b = r;
      r = a%b;
    }
    return b;
  }
}
