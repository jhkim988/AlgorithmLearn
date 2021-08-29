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
    int second = 1;
    for (int i = 1; i < skip.length; i++) {
      if (skip[i] != 0) {
        second = i - 1;
        break;
      }
    }
    int textlen = text.length();
    int patternlen = pattern.length();
    int pt = 0;
    while (pt < textlen) {
      int pp = 0;
      while (pt < textlen && pp < patternlen) {
        if (text.charAt(pt) == pattern.charAt(pp)) {
          pt++;
          pp++;
        } else if (pp == 0) {
          pt++;
        } else {
          pp = skip[pp];
        }
      }
      if (pp == patternlen) {
        sb.append(pt - pp + 1).append(" ");
        count++;
      }
      pt = pt - pp + second;
    }
    return sb;
  }
  static int[] skip(String pattern) {
    int len = pattern.length();
    int[] memo = new int[len + 1];
    int pt = 1;
    int pp = 0;
    while (pt < len) {
      if (pattern.charAt(pt) == pattern.charAt(pp)) {
        memo[++pt] = ++pp;
      } else if (pp == 0) {
        memo[++pt] = pp;
      } else {
        pp = memo[pp];
      }
    }
    return memo;
  }
}
