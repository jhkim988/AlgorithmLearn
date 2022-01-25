import java.io.*;
import java.util.*;

public class BOJ16401 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numNephew = Integer.parseInt(st.nextToken());
    int numSnack = Integer.parseInt(st.nextToken());
    int[] snack = new int[numSnack];
    st = new StringTokenizer(br.readLine());
    long totalLen = 0;
    for (int i = 0; i < numSnack; i++) {
      totalLen += snack[i] = Integer.parseInt(st.nextToken());
    }

    long lo = 0;
    long hi = totalLen / numNephew + (totalLen % numNephew == 0 ? 0 : 1);
    while (lo + 1 < hi) {
      long mid = (lo + hi) / 2;
      if (check(mid, snack, numNephew)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Long.toString(lo));
    bw.newLine();
    bw.flush();
  }
  static boolean check(long len, int[] snack, int numNephew) {
    if (len <= 0) return true;
    long num = 0;
    for (int s : snack) {
      num += s / len;
    }
    return numNephew <= num;
  }
}
