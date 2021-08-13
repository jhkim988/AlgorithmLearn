import java.io.*;
import java.util.*;

public class BOJ2096 {
  public static void main(String[] args) throws IOException {
    int[] move = {-1, 0, 1};
    final int INF = 1_000_000;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] maxDP = new int[3];
    int[] minDP = new int[3];
    for (int i = 0; i < 3; i++) {
      maxDP[i] = minDP[i] = Integer.parseInt(st.nextToken());
    }
    int[] maxtmp = new int[3];
    int[] mintmp = new int[3];
    Arrays.fill(mintmp, INF);
    for (int i = 1; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          if (0 <= j + move[k] && j + move[k] < 3) {
            maxtmp[j] = Math.max(maxtmp[j], maxDP[j + move[k]]);
            mintmp[j] = Math.min(mintmp[j], minDP[j + move[k]]);
          } 
        } 
        int input = Integer.parseInt(st.nextToken());
        maxtmp[j] += input;
        mintmp[j] += input;
      }
      for (int l = 0; l < 3; l++) {
        maxDP[l] = maxtmp[l];
        minDP[l] = mintmp[l];
        mintmp[l] = INF;
      }
    }
    int max = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
    int min = Math.min(minDP[0], Math.min(minDP[1], minDP[2]));
    bw.write(max + " " + min + "\n");
    bw.flush();
  }
}
