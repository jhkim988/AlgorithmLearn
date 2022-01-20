import java.io.*;
import java.util.*;

public class BOJ1701 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] text = br.readLine().toCharArray();
    bw.write(Integer.toString(skip(text)));
    bw.newLine();
    bw.flush();
  }
  static int skip(char[] text) {
    int maxLen = 0;
    int[] skip = new int[text.length];
    for (int startIdx = 0; startIdx < text.length; startIdx++) {
      int pt = startIdx + 1;
      int pp = startIdx;
      while (pt < text.length) {
        if (text[pt] == text[pp]) {
          skip[pt++] = ++pp - startIdx;
          if (maxLen < pp - startIdx) maxLen = pp - startIdx;
        } else if (pp == startIdx) {
          pt++;
        } else {
          pp = startIdx + skip[pp - 1];
        }
      }
      Arrays.fill(skip, 0);
    }
    return maxLen;
  }
}
