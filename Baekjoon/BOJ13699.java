import java.io.*;

public class BOJ13699 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    long[] t = new long[n+1];
    t[0] = 1;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        t[i] += t[j]*t[i-1-j];  
      }
    }
    bw.write(Long.toString(t[n]));
    bw.flush();
  }
}
