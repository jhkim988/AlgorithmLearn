import java.io.*;

public class BOJ17103 {
  public static void main(String[] args) throws IOException {
    final int MAX_INPUT = 1_000_000;
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    boolean[] dp = new boolean[MAX_INPUT + 1]; // dp[i] = false -> i is prime.(skip initialize)
    int ptr = 2;
    while (ptr * ptr <= MAX_INPUT) {
      for (int i = 2 * ptr; i <= MAX_INPUT; i += ptr) {
        dp[i] = true; // not prime
      }
      while (dp[++ptr]); // find next prime
    }
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int input = Integer.parseInt(br.readLine());
      // use two pointer
      int ptr1 = 2;
      int ptr2 = input - 2;
      int numPartition = 0;
      while (ptr1 <= ptr2) {
        if (dp[ptr1]) {
          ptr1++;
          continue;
        }
        if (dp[ptr2]) {
          ptr2--;
          continue;
        }
        if (ptr1 + ptr2 < input) {
          ptr1++;
        } else if (ptr1 + ptr2 > input) {
          ptr2--;
        } else {
          numPartition++;
          ptr1++;
          ptr2--;
        }
      }
      sb.append(numPartition).append('\n');
    }
    bw.write(sb.toString());
    bw.flush();
  }
}
