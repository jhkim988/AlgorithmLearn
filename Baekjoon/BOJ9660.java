import java.io.*;

public class BOJ9660 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());
    bw.write(skwin(n) ? "SK" : "CY");
    bw.flush();
  } 
  static boolean skwin(long n) {
    if (n == 1) return true;
    if (n == 2) return false;
    n = (n-3)%7;
    if (n < 4) return true;
    if (n == 4) return false;
    if (n == 5) return true;
    return false;
  }
}
