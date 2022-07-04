import java.io.*;
import java.util.*;

public class BOJ1893 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    while (n-- > 0) {
      char[] order = br.readLine().toCharArray();
      char[] pat = br.readLine().toCharArray();
      char[] str = br.readLine().toCharArray();

      int[] shift = new int[63];
      int[] idx = new int[128];      
      for (int i = 0; i < order.length; i++) {
        idx[order[i]] = i;
      }
      if (pat.length == 1) {
        for (int i = 0; i < str.length; i++) {
          shift[(idx[str[i]]-idx[pat[0]]+order.length)%order.length]++;
        }
      } else {
        int[] dpat = new int[pat.length-1];
        int[] dstr = new int[str.length-1];
        for (int i = 0; i < dpat.length; i++) {
          dpat[i] = (idx[pat[i+1]]-idx[pat[i]]+order.length)%order.length;
        }
        for (int i = 0; i < dstr.length; i++) {
          dstr[i] = (idx[str[i+1]]-idx[str[i]]+order.length)%order.length;
        }
        ArrayList<Integer> kmp = kmp(dstr, dpat);
        for (int i : kmp) {
          shift[(idx[str[i]]-idx[pat[0]]+order.length)%order.length]++;
        }
      }
      Queue<Integer> que = new LinkedList<>();
      for (int i = 0; i < shift.length; i++) {
        if (shift[i] == 1) que.add(i);
      }
      if (que.size() == 0) bw.write("no solution");
      else if (que.size() == 1) bw.write("unique:");
      else bw.write("ambiguous:");
      while(!que.isEmpty()) {
        bw.write(' ');
        bw.write(Integer.toString(que.poll()));
      }
      bw.newLine();
    }
    bw.flush();
  }
  static int[] pi(int[] arr) {
    int[] pi = new int[arr.length];
    int j = 0;
    for (int i = 1; i < arr.length; i++) {
      while (j > 0 && arr[i] != arr[j]) j = pi[j-1];
      if (arr[i] == arr[j]) pi[i] = ++j;
    }
    return pi;
  }
  static ArrayList<Integer> kmp(int[] str, int[] pat) {
    ArrayList<Integer> ret = new ArrayList<>();
    int[] pi = pi(pat);
    int j = 0;
    for (int i = 0; i < str.length; i++) {
      while (j > 0 && str[i] != pat[j]) j = pi[j-1];
      if (str[i] == pat[j]) {
        if (j == pat.length-1) {
          j = pi[j];
          ret.add(i-pat.length+1);
        } else j++;
      }
    }
    return ret;
  }
}
