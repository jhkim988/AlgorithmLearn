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

      int[] idx = new int[128];      
      for (int i = 0; i < order.length; i++) {
        idx[order[i]] = i;
      }

      int[] pi = new int[pat.length];
      int j = 0;
      for (int i = 1; i < pat.length; i++) {
        while (j > 0 && pat[i] != pat[j]) j = pat[j-1];
        if (pat[i] == pat[j]) pi[i] = ++j;
      }

      int k = order.length;
      HashSet<Integer> shift = new HashSet<>();
      j = 0;
    }
  }
}
