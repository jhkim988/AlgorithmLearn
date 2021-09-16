import java.io.*;
import java.util.*;

public class BOJ11722 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] seq = new int[len];
    for (int i = 0; i < len; i++) {
      seq[i] = Integer.parseInt(st.nextToken());
    }

    // bw.write(useDoubleLoop(seq) + "\n");
    bw.write(useBinSearch(seq) + "\n");
    bw.flush();
  }
  static int useDoubleLoop(int[] seq) {
    int len = seq.length;
    int[] dp = new int[len];
    int max = 0;
    for (int i = 0; i < len; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (seq[j] > seq[i] && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
        }
      }
      if (max < dp[i]) {
        max = dp[i];
      }
    }
    return max;
  }
  static int useBinSearch(int[] seq) {
    int len = seq.length;
    Integer[] table = new Integer[len + 1];
    int ptr = 1;

    for (int i = 0; i <= len; i++) {
      table[i] = 0;
    }

    for (int i = 0; i < len; i++) {
      if (table[ptr - 1] > seq[i]) {
        table[ptr++] = seq[i];
      } else {
        int idx = Arrays.binarySearch(table, 0, ptr, seq[i], Comparator.reverseOrder());
        if (idx < 0) {
          idx = -(idx + 1);
        }
        table[idx] = seq[i];
      }
    }
    return ptr;
  }
}