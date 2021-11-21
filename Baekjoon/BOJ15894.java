import java.io.*;

public class BOJ15894 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int input = Integer.parseInt(br.readLine());
    long answer = input * 4L;
    bw.write(answer + "\n");
    bw.flush();
  }
}
