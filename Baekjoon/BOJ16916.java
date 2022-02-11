import java.io.*;

public class BOJ16916 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String text = br.readLine();
    String pattern = br.readLine();
    bw.write(kmpMatch(text, pattern) ? "1\n" : "0\n");
    bw.flush();
  }
  static boolean kmpMatch(String text, String pattern) {
    char[] textArr = text.toCharArray();
    char[] patternArr = pattern.toCharArray();
    int[] skipArr = skip(patternArr);
    // System.out.println(Arrays.toString(skipArr));
    int pt = 0;
    int pp = 0;
    while (pt < textArr.length) {
      // System.out.println("text[" + pp + "] = " + textArr[pp] + " / patternArr[" + pp + "] = " + patternArr[pp]);
      if (textArr[pt] == patternArr[pp]) {
        pp++; pt++;
        if (pp == patternArr.length) return true;
        continue;
      }
      if (pp == 0) pt++;
      else pp = skipArr[pp - 1];
    }
    return false;
  }
  static int[] skip(char[] patternArr) {
    int[] memo = new int[patternArr.length];
    int pt = 1;
    int pp = 0;
    while (pt < patternArr.length) {
      if (patternArr[pt] == patternArr[pp]) memo[pt++] = ++pp;
      else if (pp == 0) memo[pt++] = 0;
      else pp = memo[pp - 1];
    }
    return memo;
  }
}
