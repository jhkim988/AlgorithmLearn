import java.io.*;
import java.util.*;

public class BOJ13904 {
  private static class Pair {
    int d, w;
    Pair(int d, int w) {
      this.d = d;
      this.w = w;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Pair[] arr = new Pair[n];
    int maxDay = 0;
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      arr[i] = new Pair(d, w);
      if (maxDay < d) maxDay = d;
    }
    Arrays.sort(arr, (a, b) -> {
      return a.d!=b.d ? a.d-b.d : a.w-b.w;
    });
    boolean[] visit = new boolean[n];
    int sum = 0;
    for (int day = maxDay; day >= 1; day--) {
      int maxScore = 0;
      int maxIdx = 0;
      for (int idx = 0; idx < n; idx++) {
        if (arr[idx].d >= day && !visit[idx] && maxScore < arr[idx].w) {
          maxScore = arr[idx].w;
          maxIdx = idx;
        }
      }
      if (maxScore == 0) continue;
      sum += maxScore;
      visit[maxIdx] = true;
    }
    bw.write(Integer.toString(sum));
    bw.flush();
  }
}