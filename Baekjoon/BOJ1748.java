import java.io.*;

public class BOJ1748 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long N = Long.parseLong(br.readLine());
    long result = 0L;
    long digit = 1L;
    int length = 1;
    while (true) {
      if (N < digit * 10L) {
        result += (N - digit + 1) * length;
        break;
      } else {
        result += 9 * digit * length;
      }
      digit *= 10;
      length++;
    }

    bw.write(result + "\n");
    bw.flush();
  }
}