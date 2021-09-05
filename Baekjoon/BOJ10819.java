import java.io.*;
import java.util.*;

public class BOJ10819 {
  static int N;
  static int[] data;
  static int maxSum = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.parseInt(br.readLine());
    data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    int[] tmp = data.clone();
    boolean[] marked = new boolean[N];
    recur(0, marked, tmp);
    bw.write(maxSum + "\n");
    bw.flush();
  }
  static void recur(int depth, boolean[] marked, int[] tmp) {
    if (depth == N) {
      int val = getValue(tmp);
      if (maxSum < val) {
        maxSum = val;
      }
      return;
    }
    for (int i = 0; i < N; i++) {
      if (marked[i]) continue;
      marked[i] = true;
      tmp[depth] = data[i];
      recur(depth + 1, marked, tmp);
      marked[i] = false;
    }
  }
  static int getValue(int[] data) {
    int sum = 0;
    for (int i = 0; i < N - 1; i++) {
      sum += Math.abs(data[i] - data[i + 1]);
    }
    return sum;
  }
}
