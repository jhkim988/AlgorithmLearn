import java.io.*;

public class BOJ9658 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    boolean[] sk = new boolean[n+1];
    sk[0] = true; sk[1] = false;
    for (int i = 2; i <= n; i++) {
      if (!sk[i-1] || (i >= 3 && !sk[i-3]) || (i >= 4 && !sk[i-4])) sk[i] = true;
      else sk[i] = false;
    }
    bw.write(sk[n] ? "SK" : "CY");
    bw.flush();
  }
}