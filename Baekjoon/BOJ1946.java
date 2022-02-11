import java.io.*;
import java.util.*;

public class BOJ1946 {
  private static class Pair implements Comparable<Pair> {
    int first, second;
    Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }
    @Override
    public int compareTo(Pair other) {
      return this.first - other.first;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());
    while (T-- > 0) {
      int n = Integer.parseInt(br.readLine());
      Pair[] arr = new Pair[n];
      for (int i = 0; i < n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        arr[i] = new Pair(first, second);
      }
      Arrays.sort(arr);
      int minOfSecond = arr[0].second;
      int num = 1;
      for (int i = 1; i < n; i++) {
        if (arr[i].second < minOfSecond) {
          num++;
          minOfSecond = arr[i].second;
        }
      }
      bw.write(Integer.toString(num));
      bw.newLine();
    }
    bw.flush();
  }
}
