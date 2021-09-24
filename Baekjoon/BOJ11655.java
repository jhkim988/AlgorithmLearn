import java.io.*;

public class BOJ11655 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    bw.write(ROT13(input));
    bw.flush();
  }
  static String ROT13(String str) {
    int len = str.length();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      char ch = str.charAt(i);
      if ('a' <= ch && ch <= 'z') {
        sb.append((char) (((ch - 'a' + 13) % 26) + 'a'));
      } else if ('A' <= ch && ch <= 'Z') {
        sb.append((char) (((ch + 13 - 'A') % 26) + 'A'));
      } else {
        sb.append(ch);
      }
    }
    sb.append('\n');
    return sb.toString();
  }
}
