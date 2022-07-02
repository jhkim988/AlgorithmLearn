import java.io.*;
import java.util.*;

public class BOJ16172 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] arr = br.readLine().toCharArray();
    ArrayList<Character> arrlist = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      if ('0' <= arr[i] && arr[i] <= '9') continue;
      arrlist.add(arr[i]);
    }
    char[] pat = br.readLine().toCharArray();
    bw.write(kmp(arrlist, pat) ? "1\n" : "0\n");
    bw.flush();
  }
  static int[] pi(char[] pat) {
    int[] pi = new int[pat.length];
    int j = 0;
    for (int i = 1; i < pat.length; i++) {
      while (j > 0 && pat[i] != pat[j]) j = pi[j-1];
      if (pat[i] == pat[j]) pi[i] = ++j;
    }
    return pi;
  } 
  static boolean kmp(ArrayList<Character> arrlist, char[] pat) {
    int[] pi = pi(pat);
    int j = 0;
    for (char ch : arrlist) {
      while (j > 0 && ch != pat[j]) j = pi[j-1];
      if (ch == pat[j]) {
        if (j == pat.length-1) return true;
        else j++;
      }
    }
    return false;
  }
}
