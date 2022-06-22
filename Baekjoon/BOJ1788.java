import java.io.*;

public class BOJ1788 {
  public static void main(String[] args) throws IOException {
    final long D = 1_000_000_000;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long[] f = new long[1_000_001];
    f[1] = 1;
    for (int i = 2; i < f.length; i++) {
      f[i] = (f[i-1] + f[i-2])%D;
    }

    int n = Integer.parseInt(br.readLine());
    if (n < 0) {
      if (n % 2 == 0) bw.write("-1\n");
      else bw.write("1\n");
      bw.write(Long.toString(f[-n]));
    } else if (n > 0) {
      bw.write("1\n");
      bw.write(Long.toString(f[n]));
    } else {
      bw.write("0\n0");
    }
    bw.flush();
  }
}