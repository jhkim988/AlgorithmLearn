import java.io.*;
import java.util.*;

public class BOJ11656 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    int len = input.length();
    String[] suffix = new String[len];
    for (int i = 0; i < len; i++) {
      suffix[i] = input.substring(i, len);
    }
    Arrays.sort(suffix);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      sb.append(suffix[i]).append('\n');
    }
    bw.write(sb.toString());
    bw.flush();
  }
}
