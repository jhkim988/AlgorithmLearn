import java.io.*;
import java.util.*;

public class BOJ21040 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] input = new int[N];
    for (int i = 0; i < N; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(input);

    int minDiff = input[1] - input[0];
    for (int i = 2; i < N; i++) {
      minDiff = Integer.min(minDiff, input[i] - input[i - 1]);
    }

    int lo = minDiff - 1;
    int hi = input[N - 1] - input[0] + 1;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (check(mid, input, K)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(lo));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int val, int[] input, int K) {
    int count = 1;
    int min = input[0];
    for (int i = 1; i < input.length; i++) {
      if (input[i] - min >= val) {
        count++;
        min = input[i];
      }
    }
    return K <= count;
  }
}
