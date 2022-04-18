import java.io.*;

public class BOJ2023 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int n;
  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());
    recur(0, 0);
    bw.flush();
  }
  static void recur(int depth, int num) throws IOException {
    if (depth == n) {
      bw.write(Integer.toString(num));
      bw.newLine();
      return;
    }
    for (int i = 0; i <= 9; i++) {
      if (!isPrime(num*10+i)) continue;
      recur(depth+1, num*10+i);
    }
  }
  static boolean isPrime(int n) {
    if (n <= 1) return false;
    for (int i = 2; i*i <= n; i++) {
      if (n % i == 0) return false;
    }
    return true;
  }
}
