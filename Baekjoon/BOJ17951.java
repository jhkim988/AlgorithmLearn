import java.io.*;
import java.util.*;

public class BOJ17951 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] scores = new int[N];
    st = new StringTokenizer(br.readLine());
    int total = 0;
    for (int i = 0; i < N; i++) {
      scores[i] = Integer.parseInt(st.nextToken());
      total += scores[i];
    }

    int lo = 0;
    int hi = total + 1;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (check(mid, scores, K)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(lo));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int val, int[] scores, int K) {
    int sum = 0;
    int count = 0;
    for (int score : scores) {
      sum += score;
      if (sum >= val) {
        count++;
        sum = 0;
      }
    }
    return K <= count;
  }
}
