import java.io.*;

public class BOJ3568 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] input = br.readLine().split(" ");
    for (int i = 1; i < input.length; i++) {
      String tmp = input[i];
      int tmpLen = tmp.length();
      int cursor = 0;
      for (; cursor < tmpLen; cursor++) {
        char tmpChar = tmp.charAt(cursor);
        if (tmpChar == '&' || tmpChar == '*' || tmpChar == '[' || tmpChar == ',' || tmpChar == ';')
          break;
      }
      String varName = tmp.substring(0, cursor);
      StringBuilder sb = new StringBuilder();
      sb.append(input[0]);
      for (int j = tmpLen - 1; j >= cursor; j--) {
        char tmpChar = tmp.charAt(j);
        if (tmpChar == ',' || tmpChar == ';') continue;
        if (tmpChar == '[') {
          sb.append(']');
        } else if (tmpChar == ']') {
          sb.append('[');
        } else {
          sb.append(tmpChar);
        }
      }
      sb.append(' ');
      sb.append(varName);
      sb.append(';');
      bw.write(sb.toString() + "\n");
    }
    bw.flush();
  }
}
