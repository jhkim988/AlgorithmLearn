import java.io.*;
import java.util.*;

public class BOJ7579 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;
  static int numApp;
  static int needMem;
  static int[] mem;
  static int[] cost;
  static int rangeCost = 10000;

  public static void main(String[] args) throws IOException {
    st = new StringTokenizer(br.readLine());
    numApp = Integer.parseInt(st.nextToken());
    needMem = Integer.parseInt(st.nextToken());
    
    mem = new int[numApp];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numApp; i++) {
      mem[i] = Integer.parseInt(st.nextToken());
    }

    cost = new int[numApp];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numApp; i++) {
      cost[i] = Integer.parseInt(st.nextToken());
    }
    st = null;

    int[] dp = new int[rangeCost + 1];
    for (int i = 1; i <= rangeCost; i++) {
      for (int j = 0; j < numApp; j++) {
        if (i >= cost[j]) {
          dp[i] = Math.max(dp[i], dp[i - cost[j]] + mem[j]);
        }        
      }
    }
    int minCost = Integer.MAX_VALUE;
    for (int i = 0; i <= rangeCost; i++) {
      if (dp[i] >= needMem && i < minCost) {
        minCost = i;
      }
    }
    bw.write(minCost + "\n");
    bw.flush();
  }
}
