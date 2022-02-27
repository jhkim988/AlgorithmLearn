import java.io.*;
import java.util.*;

public class BOJ10451 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int n = Integer.parseInt(br.readLine());
      int[] permutation = new int[n+1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= n; i++) {
        permutation[i] = Integer.parseInt(st.nextToken());
      }
      boolean[] visit = new boolean[n+1];
      int num = 0;
      for (int i = 1; i <= n; i++) {
        if (visit[i]) continue;
        num++;
        int x = i;
        while (!visit[x]) {
          visit[x] = true;
          x = permutation[x];
        }
      }
      bw.write(Integer.toString(num));
      bw.newLine();
    }
    bw.flush();
  }
}
