import java.io.*;

public class BOJ10162 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] list = {300, 60, 10};
    int[] answer = {0, 0, 0};
    for (int i = 0; i < 3; i++) {
      answer[i] += n/list[i];
      n %= list[i];
    }
    if (n != 0) {
      bw.write("-1\n");
    } else {
      for (int i = 0; i < 3; i++) {
        bw.write(Integer.toString(answer[i]));
        bw.write(' ');
      }
    }
    bw.flush();
  }
}
