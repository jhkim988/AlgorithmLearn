import java.io.*;

public class BOJ11047 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] tmp = br.readLine().split(" ");
      int N = Integer.parseInt(tmp[0]);
      int K = Integer.parseInt(tmp[1]);

      int[] coins = new int[N];
      for (int i = 0; i < N; i++) {
        coins[i] = Integer.parseInt(br.readLine());
      }

      int count = 0;
      int ptr = N - 1;
      while (K != 0) {
        while (ptr >= 0 && K < coins[ptr]) {
          ptr--;
        }
        while(ptr >= 0 && K >= coins[ptr]) {
          K -= coins[ptr];
          count++;
        }
      } 

      bw.write(count + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
