import java.io.*;

public class BOJ9655 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    boolean[] sk = new boolean[n+1];
    sk[1] = true;
    if (n > 2) {
      sk[3] = true;
    }
    if (n > 3) {
      for (int i = 4; i <= n; i++) {
        sk[i] = !(sk[i-1] && sk[i-3]);
      }
    }
    bw.write(sk[n] ? "SK\n" : "CY\n");
    bw.flush();    
  }
}
