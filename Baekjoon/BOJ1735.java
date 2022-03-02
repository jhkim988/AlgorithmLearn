import java.io.*;
import java.util.*;

public class BOJ1735 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a1 = Integer.parseInt(st.nextToken());
    int a2 = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int b1 = Integer.parseInt(st.nextToken());
    int b2 = Integer.parseInt(st.nextToken());

    int c1 = a1*b2 + a2*b1;
    int c2 = a2*b2;
    int gcd = gcd(c1, c2);
    bw.write(Integer.toString(c1/gcd));
    bw.write(' ');
    bw.write(Integer.toString(c2/gcd));
    bw.newLine();
    bw.flush();
  }
  static int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a%b);
  }
}
