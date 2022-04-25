import java.io.*;
import java.util.*;

public class BOJ2342 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    ArrayList<Integer> arr = new ArrayList<>();
    int input = Integer.parseInt(st.nextToken());
    while (input != 0) {
      arr.add(input);
      input = Integer.parseInt(st.nextToken());
    }

    final int INF = 400_000;

    // dp[i][l][r]: arr.get(i), left foot, right foot
    int[][][] dp = new int[arr.size()][5][5];
    for (int i = 0; i < arr.size(); i++) {
      for (int l = 0; l < 5; l++) {
        for (int r = 0; r < 5; r++) {
          dp[i][l][r] = INF;
        }
      }
    }

    int start = arr.get(0);
    dp[0][start][0] = dp[0][0][start] = 2;
    
    for (int i = 1; i < arr.size(); i++) {
      int num = arr.get(i);
      for (int l = 0; l < 5; l++) {
        for (int r = 0; r < 5; r++) {
          if (l == r || (l != num && r != num)) {
            dp[i][l][r] = INF;
          } else {
            if (l == num) {
              for (int ll = 0; ll < 5; ll++) {
                dp[i][l][r] = Integer.min(dp[i][l][r], dp[i-1][ll][r] + cost(ll, l));
              }
            } else {
              for (int rr = 0; rr < 5; rr++) {
                dp[i][l][r] = Integer.min(dp[i][l][r], dp[i-1][l][rr] + cost(rr, r));
              }
            }
          }
        }
      }
    }
    int min = INF;
    for (int l = 0; l < 5; l++) {
      for (int r = 0; r < 5; r++) {
        min = Integer.min(min, dp[arr.size()-1][l][r]);
      }
    }
    bw.write(Integer.toString(min));
    bw.flush();
  }
  static int cost(int prev, int next) {
    if (prev == next) return 1;
    if (prev == 0) return 2;
    if (next-prev == 2 || prev-next == 2) return 4;
    return 3;    
  }
}
