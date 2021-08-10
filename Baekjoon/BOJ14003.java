import java.io.*;
import java.util.*;

public class BOJ14003 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int len = Integer.parseInt(br.readLine());
    int[] data = new int[len];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[len];
    int[] sorted = new int[len];
    dp[0] = 0;
    sorted[0] = data[0];
    int LISlen = 1;
    for (int i = 1; i < len; i++) {
      int idx = Arrays.binarySearch(sorted, 0, LISlen, data[i]);
      if (idx > -1) {
        dp[i] = idx;
      } else {
        idx = -(idx + 1);
        sorted[idx] = data[i];
        if (idx == LISlen) {
          LISlen++;
        }
        dp[i] = idx;
      }
    }
    
    int copyLIS = LISlen;
    Stack<Integer> stk = new Stack<>();
    for (int i = len - 1; i >= 0; i--) {
      if (dp[i] == copyLIS - 1) {
        stk.push(i);
        copyLIS--;
      }
      if (!stk.empty() && stk.peek() == dp[i]) {
        if (data[stk.peek()] > data[dp[i]]) {
          stk.pop();
          stk.push(i);
        }
      }
    }

    bw.write(LISlen + "\n");
    while (!stk.empty()) {
      bw.write(data[stk.pop()] + " ");
    }
    bw.flush();
  }
}
