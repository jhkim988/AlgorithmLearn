import java.io.*;
import java.util.*;

public class BOJ2482 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());

    // dp[bit][num pick] = num case
    Queue<Integer> bitQue = new LinkedList<>();
    long[][] dp = new long[1 << N][K + 1];
    int numPick = 1;
    for (int i = 0; i < N; i++) {
      dp[1 << i][numPick] = 1;
      bitQue.add(1 << i);
    }
    bitQue.add(-1);
    while (!bitQue.isEmpty()) {
      int bit = bitQue.poll();
      if (bit == -1) {
        numPick++;
        if (bitQue.isEmpty()) {
          break;
        }
        bitQue.add(-1);
      }
      // System.out.println("bit:\t" + Integer.toBinaryString(bit));
      for (int i = 0; i < N; i++) {
        if (i == 0) {
          if ((bit & (1 << 1)) != 0 || (bit & (1 << N - 1)) != 0) continue;
        } else if (i == N - 1) {
          if ((bit & 1) != 0 || (bit & (1 << N - 2)) != 0) continue;
        } else {
          if (((bit & (1 << (i - 1))) != 0 || (bit & (1 << (i + 1))) != 0)) continue;
        }
        if ((bit & (1 << i)) == 0) {
          bitQue.add(bit | (1 << i));
        } else {
          continue;
        }
        // System.out.printf("%d: %d add:\t%s\n", numPick, i, Integer.toBinaryString((bit | (1 << i))));
        // System.out.printf("dp[bit]: %d\n", dp[bit][numPick]);
        dp[bit | (1 << i)][numPick + 1] += dp[bit][numPick];
      }
    }
    long sum = 0;
    for (int i = 0; i < 1<<N; i++) {
      sum += dp[i][K];
    }
    bw.write(sum+ "\n");
    bw.flush();
  }
  static void recur(Queue<Integer> que) {

  }
}
