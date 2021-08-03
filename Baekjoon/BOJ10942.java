import java.io.*;
import java.util.*;

public class BOJ10942 {
  static int len;
  static int[] data;
  static boolean[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    len = Integer.parseInt(br.readLine());
    data = new int[len];
    dp = new boolean[len][len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    } 
    init();
    int numQ = Integer.parseInt(br.readLine());
    for (int i = 0; i < numQ; i++) {
      st = new StringTokenizer(br.readLine());
      bw.write((dp[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] ? 1 : 0)+ "\n");
    }
    bw.flush();
  }
  static void init() {
    for (int i = 0; i < len; i++) {
      dp[i][i] = true;
    }
    for (int i = 0; i + 1 < len; i++) {
      dp[i][i + 1] = data[i] == data[i + 1];
    }
    for (int diff = 2; diff < len; diff++) {
      for (int i = 0; i + diff < len; i++) {
        dp[i][i + diff] = dp[i + 1][i + diff - 1] ? data[i] == data[i + diff] : false;        
      }
    }
  }
}
