import java.io.*;
import java.util.*;

public class BOJ9576 {
  private static class Pair {
    int lo, hi;
    Pair(int lo, int hi) {
      this.lo = lo;
      this.hi = hi;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      Pair[] arr = new Pair[m];
      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int lo = Integer.parseInt(st.nextToken());
        int hi = Integer.parseInt(st.nextToken());
        arr[i] = new Pair(lo, hi);
      }
      Arrays.sort(arr, (a, b) -> {
        return a.hi != b.hi ? a.hi-b.hi : a.lo-b.lo;
      });

      int num = 0;
      boolean[] visit = new boolean[n+1];
      for (int i = 0; i < m; i++) {
        for (int j = arr[i].lo; j <= arr[i].hi; j++) {
          if (visit[j]) continue;
          num++;
          visit[j] = true;
          break;
        }
      }
      bw.write(Integer.toString(num));
      bw.newLine();
    }
    bw.flush();
  }
}
