import java.io.*;

public class BOJ1786 {
  static int count = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String text = br.readLine();
    String pattern = br.readLine();
    StringBuilder result = kmpMatch(text, pattern);
    bw.write(count + "\n");
    bw.write(result.append("\n").toString());
    bw.flush();
  }
  static StringBuilder kmpMatch(String text, String pattern) {
    StringBuilder sb = new StringBuilder();
    int[] skip = skip(pattern);
    int textlen = text.length();
    int patternlen = pattern.length();
    int pt = 0;
    int pp = 0;
    while (pt < textlen) {
      if (text.charAt(pt) == pattern.charAt(pp)) {
        pt++;
        pp++;
      } 
      if (pp == patternlen) {
        sb.append(pt - pp + 1).append(" ");
        count++;
        pp = skip[pp - 1];
      } else if (pt < textlen && pattern.charAt(pp) != text.charAt(pt)) {
        if (pp != 0) {
          pp = skip[pp - 1];
        } else {
          pt++;
        }
      }      
    }
    return sb;
  }
  static int[] skip(String pattern) {
    int len = pattern.length();
    int[] memo = new int[len];
    memo[0] = 0;
    int pt = 1;
    int pp = 0;
    while (pt < len) {
      if (pattern.charAt(pt) == pattern.charAt(pp)) {
        pp++;
        memo[pt] = pp;
        pt++;
      } else if (pp == 0) {
        memo[pt] = pp;
        pt++;
      } else {
        pp = memo[pp - 1];
      }
    }
    return memo;
  }
}
