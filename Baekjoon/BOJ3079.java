import java.io.*;
import java.util.*;

public class BOJ3079 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numWindow = Integer.parseInt(st.nextToken());
    long numFriend = Long.parseLong(st.nextToken());
    long[] cost = new long[numWindow];
    for (int i = 0; i < numWindow; i++) {
      cost[i] = Integer.parseInt(br.readLine());
    }

    long lo = -1;
    long hi = Long.MAX_VALUE;
    while (lo+1 < hi) {
      long mid = lo/2 + hi/2 + addVal(lo, hi);
      if (possible(mid, cost, numFriend)) {
        hi = mid;
      } else {
        lo = mid;
      }
    }

    bw.write(Long.toString(hi));
    bw.newLine();
    bw.flush();
  }
  private static long addVal(long a, long b) {
    return (a%2 == 1 && b%2 == 1) ? 1 : 0;
  }
  static boolean possible(long time, long[] cost, long numFriend) {
    int numPass = 0;
    for (long c : cost) {
      if (numFriend - time/c <= numPass) return true;
      numPass += time / c;
    }
    return numFriend <= numPass;
  }
}
