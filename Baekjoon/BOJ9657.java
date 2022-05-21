import java.io.*;

public class BOJ9657 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    boolean[] skwin = new boolean[n+1];
    if (n >= 1) skwin[1] = true;
    if (n >= 3) skwin[3] = true;
    if (n >= 4) skwin[4] = true;
    for (int i = 5; i <= n; i++) {
      skwin[i] = !skwin[i-1] || !skwin[i-3] || !skwin[i-4];
    }
    bw.write(skwin[n] ? "SK\n" : "CY\n");
    bw.flush();
  }
}
