import java.io.*;
import java.util.*;

public class BOJ15824 {
  static final int R = 1_000_000_007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    long[] arr = new long[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    long[] pSum = new long[n];
    pSum[0] = arr[0] % R;
    for (int i = 1; i < n; i++) {
      pSum[i] = (pSum[i-1] + arr[i]) % R;
    }
    long answer = 0;
    long exp = 1;
    for (int gap = 0; gap < n-1; gap++) {
      answer = (answer + subtract(subtract(pSum[n-1], pSum[n-2-gap]), pSum[gap])*exp%R)%R;
      exp = (exp*2)%R;
    }
    bw.write(Long.toString(answer));
    bw.flush();
  }
  static long subtract(long a, long b) {
    return ((a%R) + (R-(b%R))) % R;
  }
}
