import java.io.*;
import java.util.*;

public class BOJ1015 {
  private static class Pair {
    int id, val;
    Pair(int id, int val) {
      this.id = id;
      this.val = val;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Pair[] a = new Pair[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      int val = Integer.parseInt(st.nextToken());
      a[i] = new Pair(i, val);  
    }
    Arrays.sort(a, (x, y) -> x.val!=y.val ? x.val-y.val : x.id-y.id);
    int[] p = new int[n];
    for (int i = 0; i < n; i++) {
      p[a[i].id] = i;
    }
    for (int i = 0; i < n; i++) {
      bw.write(Integer.toString(p[i]));
      bw.write(' ');
    }
    bw.flush();
  }
}
