import java.io.*;
import java.util.*;

public class BOJ17087 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numTarget = Integer.parseInt(st.nextToken());
    long startPoint = Long.parseLong(st.nextToken());
    long[] targetPos = new long[numTarget + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numTarget; i++) {
      targetPos[i] = Long.parseLong(st.nextToken());
    }
    targetPos[numTarget] = startPoint;
    Arrays.sort(targetPos);

    long[] diff = new long[numTarget];
    for (int i = 0; i < numTarget; i++) {
      diff[i] = targetPos[i + 1] - targetPos[i];
    }

    bw.write(gcd(diff) + "\n");
    bw.flush();
  }
  static long gcd(long[] diff) {
    long result = diff[0];
    for (int i = 1; i < diff.length; i++) {
      result = gcd(result, diff[i]);
    }
    return result;
  }
  static long gcd(long a, long b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
