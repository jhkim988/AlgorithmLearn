import java.io.*;

public class BOJ24416 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    long[] fib = new long[n+1];
    fib[1] = fib[2] = 1;
    for (int i = 3; i <= n; i++) {
      fib[i] = fib[i-1]+fib[i-2];
    }
    bw.write(Long.toString(fib[n]));
    bw.write(' ');
    bw.write(Long.toString(n-2));
    bw.flush();
  }
}
