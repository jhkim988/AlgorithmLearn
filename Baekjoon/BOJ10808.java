import java.io.*;

public class BOJ10808 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    int len = input.length();
    int[] stat = new int[26];
    for (int i = 0; i < len; i++) {
      stat[input.charAt(i) - 'a']++;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      sb.append(stat[i]).append(' ');
    }
    sb.append('\n');

    bw.write(sb.toString());
    bw.flush();
  }
}
