import java.io.*;

public class BOJ2446 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    for (int i = n-1; i >=0 ; i--) {
      for (int j = n-1; j > i; j--) {
        bw.write(' ');
      }
      for (int j = 0; j < 2*i+1; j++) {
        bw.write('*');
      }
      bw.newLine();
    }
    for (int i = 1; i <= n-1; i++) {
      for (int j = 0; j < n-i-1; j++) {
        bw.write(' ');
      }
      for (int j = 0; j < 2*i+1; j++) {
        bw.write('*');
      }
      bw.newLine();
    }
    bw.flush();
  }
}
