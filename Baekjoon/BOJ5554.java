import java.io.*;

public class BOJ5554 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int sum = 0;
    for (int i = 0; i < 4; i++) {
      sum += Integer.parseInt(br.readLine());
    }
    bw.write(Integer.toString(sum/60));
    bw.newLine();
    bw.write(Integer.toString(sum%60));
    bw.flush();
  }
}
