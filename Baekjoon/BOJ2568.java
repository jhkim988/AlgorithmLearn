import java.io.*;
import java.util.*;

public class BOJ2568 {
  private static class Pair {
    int index, value;
    Pair(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numWire = Integer.parseInt(br.readLine());
    Pair[] wires = new Pair[numWire];
    for (int i = 0; i < numWire; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      wires[i] = new Pair(start, end);
    }
    Arrays.sort(wires, (p1, p2) -> p1.index - p2.index);

    int[] hist = new int[numWire];
    int[] dp = new int[numWire + 1];
    int ptr = 1;
    for (int i = 0; i < numWire; i++) {
      if (wires[dp[ptr - 1]].value <= wires[i].value) {
        hist[i] = ptr;
        dp[ptr++] = i;
      } else {
        // binary search:
        int key = wires[i].value;
        int lo = 0; int hi = ptr - 1;
        while (lo + 1 < hi) {
          int mid = (lo + hi) / 2;
          if (wires[dp[mid]].value <= key) { // check(x) = wires[dp[x]].value <= key
            lo = mid;
          } else {
            hi = mid;
          }
        }
        hist[i] = hi;
        dp[hi] = i;
      }
    }
    int lenLIS = ptr - 1;
    bw.write(Integer.toString(numWire - lenLIS));
    bw.newLine();

    boolean[] memLIS = new boolean[numWire];
    for (int i = numWire - 1; i >= 0; i--) {
      if (hist[i] == lenLIS) {
        memLIS[i] = true;
        lenLIS--;
      }
    }

    for (int i = 0; i < numWire; i++) {
      if (memLIS[i]) continue;
      bw.write(Integer.toString(wires[i].index));
      bw.newLine();
    }
    bw.flush();
  }
}
