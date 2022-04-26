import java.io.*;

public class BOJ20944 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    while (n-- > 0) {
      bw.write('a');
    }
    bw.flush();
  }
}