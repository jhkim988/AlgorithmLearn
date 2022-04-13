import java.io.*;

public class BOJ14916 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    if (n == 1 || n == 3) {
      bw.write("-1\n");
      bw.flush();
      return;
    }
    int num5 = n/5 - ((n-n/5*5) % 2 == 0 ? 0 : 1);
    int num2 = (n-num5*5)/2;
    bw.write(Integer.toString(num5 + num2));
    bw.newLine();
    bw.flush();
  }
}
