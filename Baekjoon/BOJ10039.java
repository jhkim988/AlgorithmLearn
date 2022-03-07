import java.io.*;

public class BOJ10039 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      int input = Integer.parseInt(br.readLine());
      if (input < 40) sum += 40;
      else sum += input;
    }
    bw.write(Integer.toString(sum/5));
    bw.newLine();
    bw.flush();
  }
}