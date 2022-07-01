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
        while (j > 0 && pat[i] != pat[j]) j = pi[j-1];
        if (pat[i] == pat[j]) pi[i] = ++j;
      }
      System.out.println("pi: " + Arrays.toString(pi));
      int k = order.length;
      int offSet = (idx[str[0]]-idx[pat[0]]+k)%k;
      int[] shift = new int[63];
      j = 0;
      for (int i = 0; i < str.length; i++) {
        while (j > 0 && idx[str[i]] != (idx[pat[j]]+offSet)%k) j = pi[j-1];
        if (idx[str[i]] == (idx[pat[j]]+offSet)%k) {
          if (j == pat.length-1) {
            j = pi[j];
            shift[offSet]++;
            offSet = (idx[str[i-pat.length+1]]-idx[pat[0]]+k)%k;
          } else {
            j++;
          }
        } else {
          offSet = (idx[str[i]]-idx[pat[j]]+k)%k;
          i--;
        }
      }

      Queue<Integer> que = new LinkedList<>();
      for (int i = 0; i < shift.length; i++) {
        if (shift[i] == 1) que.add(i);
      }
      if (que.size() == 0) bw.write("no solution");
      else if (que.size() == 1) bw.write("unique: ");
      else bw.write("ambiguous: ");
      while(!que.isEmpty()) {
        bw.write(Integer.toString(que.poll()));
        bw.write(' ');
      }
      bw.newLine();
    }
    bw.flush();
  }
}
