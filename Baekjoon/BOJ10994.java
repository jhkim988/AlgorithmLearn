import java.io.*;

public class BOJ10994 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());

    String front = "* ";
    String back = " *";

    for (int i = 0; i < 2*n-1; i++) {
      if (i % 2 == 0) {
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(front);
        }
        for (int j = 0; j < 4*n-3-2*i; j++) {
          bw.write('*');
        }
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(back);
        }
      } else {
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(front);
        }
        for (int j = 0; j < 4*n-3-2*i-2; j++) {
          bw.write(' ');
        }
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(back);
        }
      }
      bw.newLine();
    }
    for (int i = 2*n-3; i >= 0; i--) {
      if (i % 2 == 0) {
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(front);
        }
        for (int j = 0; j < 4*n-3-2*i; j++) {
          bw.write('*');
        }
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(back);
        }
      } else {
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(front);
        }
        for (int j = 0; j < 4*n-3-2*i-2; j++) {
          bw.write(' ');
        }
        for (int j = 0; j < (i+1)/2; j++) {
          bw.write(back);
        }
      }
      bw.newLine();
    }
    bw.flush();
  }
}