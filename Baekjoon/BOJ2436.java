import java.io.*;
import java.util.*;

public class BOJ2436 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int g = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    int x = l/g;
    int rx = (int) Math.sqrt(x);
    int a = 0, b = 0;
    for (int i = rx; i > 0; i--) {
      if (x % i != 0) continue;
      a = i;
      b = x/i;
      if (gcd(a, b) == 1) break;
    }
    bw.write(Integer.toString(a*g));
    bw.write(' ');
    bw.write(Integer.toString(b*g));
    bw.flush();
  }
  static int gcd(int a, int b) {
    int r = a%b;
    while (r != 0) {
      int tmp = b%r;
      a = b;
      b = r;
      r = tmp;
    }
    return b;
  }
}
