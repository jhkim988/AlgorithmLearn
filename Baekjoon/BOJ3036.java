import java.io.*;
import java.util.*;

public class BOJ3036 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numRing = Integer.parseInt(br.readLine());
    int[] ring = new int[numRing];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numRing; i++) {
      ring[i] = Integer.parseInt(st.nextToken());
    }

    int gcd;
    for (int i = 1; i < numRing; i++) {
      gcd = gcd(ring[i], ring[0]);
      bw.write(ring[0]/gcd + "/" + ring[i]/gcd + "\n");
    }
    bw.flush();
  } 
  static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    } else {
      return gcd(b, a % b);
    }
  }
}
