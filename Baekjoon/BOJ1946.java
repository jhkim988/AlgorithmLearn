import java.io.*;
import java.util.*;

public class BOJ1946 {
  private static class Pair {
    int first, second, min, max;
    Pair(int first, int second) {
      this.first = first;
      this.second = second;
      if (first < second) {
        min = first;
        max = second;
      } else {
        max = first;
        min = second;
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      int n = Integer.parseInt(br.readLine());
      Pair[] arrMin = new Pair[n];
      Pair[] arrMax = new Pair[n];
      for (int i = 0; i < n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        arrMax[i] = arrMin[i] = new Pair(first, second);
      }
      Arrays.sort(arrMin, (a, b) -> a.min - b.min);
      Arrays.sort(arrMax, (a, b) -> a.max - b.max);

      int lo = -1;
      int hi = n;
      while (lo + 1 < hi) {
        int mid = (lo + hi) >> 1;
        if (arrMax[mid].max < arrMin[n-1].min) {
          lo = mid;
        } else {
          hi = mid;
        }
      }

      bw.write(Integer.toString(n - lo));
      bw.newLine();
    }
    bw.flush();
  }
}
