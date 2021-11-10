import java.io.*;

public class BOJ4375 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = "";
    while ((input = br.readLine()) != null) {
      int num = Integer.parseInt(input);
      bw.write(answer(num) + "\n");
      bw.flush();
    }
  }
  static int answer(int input) {
    int num = 0;
    for (int i = 1; i <= input; i++) {
      num = num * 10 + 1;
      num %= input;
      if (num == 0) return i;
    }
    return -1;
  }
}
