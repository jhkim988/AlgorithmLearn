import java.util.Scanner;
import java.io.*;

public class BOJ15649 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    int M = scn.nextInt();
    scn.close();
  
    recur(N, M);
    try {
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
  static void recur(int N, int M) {
    int[] candidate = new int[N];
    int[] result = new int[M];

    for (int i = 0; i < N; i++) {
      candidate[i] = (i + 1);
    }

    recur(candidate, 0, M, result);
  }
  static void recur(int[] candidate, int currentDepth, int limitDepth, int[] result) {
    if (currentDepth < limitDepth) {
      for (int i = 0; i < candidate.length; i++) {
        if (candidate[i] == 0) {
          continue;
        }
        result[currentDepth] = candidate[i];
        int[] nextCand = new int[candidate.length];
        for (int j = 0; j < nextCand.length; j++) {
          if (candidate[i] != candidate[j]) {
            nextCand[j] = candidate[j];
          } 
        }
        recur(nextCand, currentDepth + 1, limitDepth, result);
      }
    } else {
      try {
        for (int i = 0; i < result.length; i++) {
          bw.write(result[i] + " ");
        }
        bw.write("\n");
      } catch (IOException e) {
        // do nothing
      }
    }
  }
}
