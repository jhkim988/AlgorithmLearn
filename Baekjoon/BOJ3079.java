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

    long min = cost[0];
    long max = cost[0];
    for (int i = 0; i < numWindow; i++) {
      min = Long.min(min, cost[i]);
      max = Long.max(max, cost[i]);
    }

    long lo = min - 1;
    long hi = max * numFriend + 1;
    while (lo + 1 < hi) {
      long mid = (lo + hi) / 2;
      if (!possible(mid, cost, numFriend)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Long.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean possible(long time, long[] cost, long numFriend) {
    long numPass = 0;
    for (long c : cost) {
      numPass += time / c;
    }
    return numFriend <= numPass;
  }
}
