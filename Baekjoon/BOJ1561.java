import java.io.*;
import java.util.*;

public class BOJ1561 {
  static long N;
  static int M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Long.parseLong(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[] runningTime = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      runningTime[i] = Integer.parseInt(st.nextToken());
    }

    if (N <= M) {
      bw.write(N + "\n");
      bw.flush();
      return;
    }

    long totalRunningTime = getTotalRun(runningTime); 
    int idx = findIdx(runningTime, totalRunningTime);
    bw.write((idx + 1) + "\n");
    bw.flush();

    // System.out.println("totalRunningTime: " + totalRunningTime);
    // System.out.println("idx: " + idx);
  }
  static int findIdx(int[] runningTime, long totalRunningTime) {
    int idx = 0;
    int count = 0;
    for (int i = 0; i < M; i++) {
      count += (totalRunningTime - 1) / runningTime[i] + 1;
    }
    while (true) {
      if (totalRunningTime % runningTime[idx] == 0) count++;
      if (count >= N) break;
      idx++;
    }
    return idx;
  }
  static long getTotalRun(int[] runningTime) {
    long lo = 0L;
    long hi = 30L * N;
    while (lo < hi) {
      long mid = (lo + hi) / 2L;
      // System.out.println("lo: " + lo + ", hi: " + hi);
      if (isPossible(runningTime, mid)) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }
  static boolean isPossible(int[] runningTime, long key) {
    long count = 0L;
    for (int i = 0; i < M; i++) {
      count += key / runningTime[i] + 1;
    }
    return count >= N;
  }
}