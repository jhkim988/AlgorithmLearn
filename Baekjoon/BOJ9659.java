import java.io.*;

public class BOJ9659 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());
    bw.write(n % 2 == 0 ? "CY" : "SK");
    bw.flush();
  }
}
