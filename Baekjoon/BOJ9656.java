import java.io.*;

public class BOJ9656 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int len = n < 4 ? 4 : n;
    boolean[] skWin = new boolean[len+1];
    skWin[2] = true;
    for (int i = 4; i <= len; i++) {
      skWin[i] = !skWin[i-1] || !skWin[i-3];
    }
    bw.write(skWin[n] ? "SK\n" : "CY\n");
    bw.flush();
  }
}
