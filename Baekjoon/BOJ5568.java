import java.io.*;
import java.util.*;

public class BOJ5568 {
  static int n, k;
  static int[] arr;
  static boolean[] visit;
  static HashSet<Long> hs;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    k = Integer.parseInt(br.readLine());
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    visit = new boolean[n];
    hs = new HashSet<>();

    recur(0, 0);

    bw.write(Integer.toString(hs.size()));
    bw.flush();
  }
  static void recur(int depth, long acc) {
    if (depth >= k) {
      hs.add(acc);
      return;
    }
    for (int i = 0; i < n; i++) {
      if (visit[i]) continue;
      visit[i] = true;
      recur(depth+1, (arr[i] >= 10 ? 100 : 10)*acc + arr[i]);
      visit[i] = false;
    }
  }
}