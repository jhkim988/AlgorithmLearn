import java.io.*;

public class BOJ2720 {
  public static void main(String[] args) throws IOException {
    int[] unit = {25, 10, 5, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      for (int i = 0; i < 4; i++) {
        bw.write(Integer.toString(n/unit[i]));
        bw.write(' ');
        n -= n/unit[i]*unit[i];
      }
      bw.newLine();
    }
    bw.flush();
  }
}
