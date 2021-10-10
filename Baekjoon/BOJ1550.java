import java.io.*;

public class BOJ1550 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int len = input.length;
    long exp = 1;
    long result = 0;
    for (int i = len - 1; i >= 0; i--) {
      result += exp * toHex(input[i]);
      exp *= 16;
    }
    bw.write(result + "\n");
    bw.flush();
  }
  static int toHex(char ch) {
    if ('0' <= ch && ch <= '9') {
      return ch - '0';
    } else {
      return ch - 'A' + 10;
    }
  }
}
