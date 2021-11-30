import java.io.*;
import java.util.*;

public class BOJ12970 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    
    bw.write(answer(N, K));
    bw.flush();
  }
  static String answer(int N, int K) {
    int numA = N/2;
    int numB = N - N/2;
    if (numA * numB < K) {
      return "-1\n";
    }
    while (K < numA * numB) {
      numA--;
      numB++;
    }
    StringBuilder sb = new StringBuilder();
    if (K == numA * numB) {
      for (int i = 0; i < numA; i++) {
        sb.append('A');
      }
      for (int i = 0; i < numB; i++) {
        sb.append('B');
      }
      sb.append('\n');
      return sb.toString();
    }
    int r = K - numA * (numB - 1);
    for (int i = 0; i < numA; i++) {
      sb.append('A');
    }
    for (int i = 0; i < numB - r - 1; i++) {
      sb.append('B');
    }
    sb.append('A');
    for (int i = 0; i < r; i++) {
      sb.append('B');
    }
    sb.append('\n');
    // System.out.println(numA + ", " + numB + ", " + r);
    return sb.toString();
  }
}
