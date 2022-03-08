import java.io.*;

public class BOJ1543 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] text = br.readLine().toCharArray();
    char[] pattern =  br.readLine().toCharArray();
    int num = kmp(text, pattern);

    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
  static int kmp(char[] text, char[] pattern) {
    int num = 0;
    int[] skip = skip(pattern);
    int pp = 0;
    int pt = 0;
    while (pt < text.length) {
      if (text[pt] == pattern[pp]) {
        pt++; pp++;
      }
      if (pp == pattern.length) {
        // find: pt - pp + 1
        num++;
        pp = 0;
      } else if (pt < text.length && text[pt] != pattern[pp]) {
        if (pp != 0) {
          pp = skip[pp-1];
        } else {
          pt++;
        }
      }
    }
    return num;
  }
  static int[] skip(char[] pattern) {
    int[] skip = new int[pattern.length];
    int pp = 0, pt = 1;
    while (pt < pattern.length) {
      if (pattern[pt] == pattern[pp]) {
        skip[pt++] = ++pp;
      } else if (pp == 0) {
        skip[pt++] = 0;
      } else {
        pp = skip[pp - 1];
      }
    }
    return skip;
  }
}