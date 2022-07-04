import java.io.*;
import java.util.*;

public class BOJ2401 {
  static int n, answer;
  static char[] str;
  static ArrayList<Pair> arrlist;
  static int[] dp;
  private static class Pair {
    int idx, npat;
    Pair(int idx, int npat) {
      this.idx = idx;
      this.npat = npat;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    str = br.readLine().toCharArray();
    n = Integer.parseInt(br.readLine());
    char[][] pats = new char[n][];
    for (int i = 0; i < n; i++) {
      pats[i] = br.readLine().toCharArray();
    }
    arrlist = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int idx : kmp(str, pats[i])) {
        arrlist.add(new Pair(idx, pats[i].length));
      }
    }
    Collections.sort(arrlist, (a, b) -> a.idx-b.idx);
    dp = new int[n];
    for (int i = 0; i < n; i++) {
      recur(i);
    }
    bw.write(Integer.toString(answer));
    bw.flush();
  }
  static int recur(int idx) {
    System.out.println("idx: " + idx);
    if (idx >= n) return 0;
    if (dp[idx] != 0) return dp[idx];
    int max = 0;
    int lo = inf(idx);
    int hi = sup(idx);
    if (arrlist.get(lo).idx != idx && arrlist.get(hi).idx != idx) return 0;
    for (int i = lo; i <= hi; i++) {
      System.out.println(arrlist.get(i).npat);
      max = Integer.max(max, recur(idx + arrlist.get(i).npat-1)+arrlist.get(i).npat);
    }
    return answer = Integer.max(answer, dp[idx] = max);
  }
  static int inf(int key) {
    int lo = -1, hi = arrlist.size();
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arrlist.get(mid).idx < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
  static int sup(int key) {
    int lo = -1, hi = arrlist.size();
    while(lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arrlist.get(mid).idx <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
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
  static ArrayList<Integer> kmp(char[] str, char[] pat) {
    ArrayList<Integer> ret = new ArrayList<>();
    int[] pi = pi(pat);
    int j = 0;
    for (int i = 0; i < str.length; i++) {
      while (j > 0 && str[i] != pat[j]) j = pi[j-1];
      if (str[i] == pat[j]) {
        if (j == pat.length-1) {
          j = pi[j];
          ret.add(i-pat.length+1);
        } else {
          j++;
        }
      }
    }
    return ret;
  }
}
