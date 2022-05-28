import java.io.*;
import java.util.*;

public class BOJ16287 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int w = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int[] set = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      set[i] = Integer.parseInt(st.nextToken());
    }
    bw.write(check(w, set) ? "YES\n" : "NO\n");
    bw.flush();
  }
  static boolean check(int w, int[] set) {
    boolean[] check = new boolean[800_001];
    for (int i = 0; i < set.length; i++) {
      for (int j = i+1; j < set.length; j++) {
        if (set[i]+set[j] > w) continue;
        if (check[w-set[i]-set[j]]) return true;
      }
      for (int j = 0; j < i; j++) {
        if (set[i]+set[j] < w) check[set[i]+set[j]] = true;
      }
    }
    return false;
  }
}
