import java.io.*;

public class BOJ1934 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    String[] tmp;
    for (int i = 0; i < numTest; i++) {
      tmp = br.readLine().split(" ");
      bw.write(lcm(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])) + "\n");
    }
    bw.flush();
  }
  static int lcm (int a, int b) {
    return a * b / gcd(a, b);
  }
  static int gcd (int a, int b) {
    if (a < b) {
      int tmp = b;
      b = a;
      a = tmp;
    }
    if (a % b == 0) {
      return b;
    } else {
      return gcd(b, a % b);
    }
  }
}
