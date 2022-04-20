import java.io.*;

public class BOJ10992 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    
    // top:
    for (int i = 0; i < n-1; i++) {
      bw.write(' ');
    }
    bw.write('*');
    bw.newLine();
    bw.flush();
    if (n == 1) return;
    
    // middle:
    for (int i = 1; i < n-1; i++) {
      for (int j = 0; j < n-i-1; j++) {
        bw.write(' ');
      }
      bw.write('*');
      for (int j = 0; j < 2*i-1; j++) {
        bw.write(' ');
      }
      bw.write('*');
      bw.newLine();
    }
    // bottom:
    for (int i = 0; i < 2*n-1; i++) {
      bw.write('*');
    }
    bw.flush();
  }
}