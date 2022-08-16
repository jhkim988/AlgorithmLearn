import java.io.*;

public class BOJ9996 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[] pat = br.readLine().toCharArray();
    int posStar = getPosStar(pat);
    while (n-- > 0) {
      if (match(br.readLine().toCharArray(), pat, posStar)) {
        bw.write("DA\n");
      } else {
        bw.write("NE\n");
      }
    }
    bw.flush();
  }
  static int getPosStar(char[] pat) {
    int posStar = 0;
    for (; posStar < pat.length; posStar++) {
      if (pat[posStar] == '*') break;
    }
    return posStar;
  }
  static boolean match(char[] str, char[] pat, int posStar) {
    if (str.length < pat.length-1) return false;
    // 앞 부분
    for (int i = 0; i < posStar; i++) {
      if (str[i] != pat[i]) return false;
    }
    // 뒷 부분
    for (int i = 0; i < pat.length-posStar-1; i++) {
      if (str[str.length-1-i] != pat[pat.length-1-i]) return false;
    }
    return true;
  }
}
