import java.io.*;

public class BOJ9253 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str1 = br.readLine().toCharArray();
    char[] str2 = br.readLine().toCharArray();
    char[] pat = br.readLine().toCharArray();
    if (kmp(str1, pat) && kmp(str2, pat)) bw.write("YES\n");
    else bw.write("NO\n");
    bw.flush();
  }
  static boolean kmp(char[] str, char[] pat) {
    int[] pi = pi(pat);
    int j = 0;
    for (int i = 0; i < str.length; i++) {
      while (j > 0 && str[i] != pat[j]) j = pi[j-1];
      if (str[i] == pat[j]) {
        if (j == pat.length-1) return true;
        else j++;
      }
    }
    return false;
  }
  static int[] pi(char[] arr) {
    int[] pi = new int[arr.length];
    int j = 0;
    for (int i = 1; i < arr.length; i++) {
      while (j > 0 && arr[i] != arr[j]) j = pi[j-1];
      if (arr[i] == arr[j]) pi[i] = ++j;
    }
    return pi;
  }
}
