import java.io.*;

public class BOJ10820 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    String input = "";
    StringBuilder sb = new StringBuilder();
    while ((input = br.readLine()) != null) {
      int len = input.length();
      int numLower = 0;
      int numUpper = 0;
      int numNum = 0;
      int numSpace = 0;
      for (int i = 0; i < len; i++) {
        char ch = input.charAt(i);
        if (ch == ' ') {
          numSpace++;
        } else if ('0' <= ch && ch <= '9') {
          numNum++;
        } else if ('A' <= ch && ch <= 'Z') {
          numUpper++;
        } else {
          numLower++;
        }
      }
      sb.append(numLower).append(' ').append(numUpper).append(' ').append(numNum).append(' ').append(numSpace).append('\n');
    }
    bw.write(sb.toString());
    bw.flush();
  }
}
