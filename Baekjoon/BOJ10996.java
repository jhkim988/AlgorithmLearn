import java.io.*;

public class BOJ10996 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    if (n == 1) {
      bw.write('*');
      bw.newLine();
      bw.flush();
      return;
    }
    int down = n/2;
    int up = n - down;
    for (int __ = 0; __ < n; __++) {
      bw.write('*');
      for (int i = 0; i < up-1; i++) {
        bw.write(' ');
        bw.write('*');
      }
      bw.newLine();
      bw.write(' ');
      bw.write('*');
      for (int i = 0; i < down-1; i++) {
        bw.write(' ');
        bw.write('*');
      }
      bw.newLine();
    }
    bw.flush();
  }
}
