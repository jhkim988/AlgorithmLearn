import java.io.*;

public class BOJ2607 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[] pat = br.readLine().toCharArray();
    int[] patNum = component(pat);
    int num = 0;
    while (--n > 0) {
      char[] str = br.readLine().toCharArray();
      if (isSimilar(pat, str, patNum, component(str))) {
        num++;
      }
    }

    bw.write(Integer.toString(num));
    bw.flush();
  }
  
  static int[] component(char[] str) {
    int[] component = new int['Z'-'A'+1];
    for (int i = 0; i < str.length; i++) {
      component[str[i]-'A']++;
    }
    return component;
  }
  static boolean isSimilar(char[] pat, char[] str, int[] cpat, int[] cstr) {
    if (pat.length-str.length > 1 || pat.length-str.length < -1) return false;
    if (pat.length == str.length) {
      int numDiff = 0;
      for (int i = 0; i < cstr.length; i++) {
        int diff = cstr[i] - cpat[i];
        if (diff < 0) diff = -diff;
  
        if (diff > 1) return false;
        else if (diff == 1) numDiff++;
      }
      return numDiff <= 2;
    } else {
      int numDiff = 0;
      for (int i = 0; i < cstr.length; i++) {
        int diff = cstr[i] - cpat[i];
        if (diff < 0) diff = -diff;

        if (diff > 1) return false;
        else if (diff == 1) numDiff++;
      }
      return numDiff < 2;
    }

  }
}