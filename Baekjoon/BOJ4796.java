import java.io.*;
import java.util.*;

public class BOJ4796 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int L = Integer.parseInt(st.nextToken());
    int P = Integer.parseInt(st.nextToken());
    int V = Integer.parseInt(st.nextToken());
    int testId = 1;
    while (L != 0 && P != 0 && V != 0) {
      long answer = answer(L, P, V);
      bw.write("Case ");
      bw.write(Integer.toString(testId));
      bw.write(": ");
      bw.write(Long.toString(answer));
      bw.newLine();

      st = new StringTokenizer(br.readLine());
      L = Integer.parseInt(st.nextToken());
      P = Integer.parseInt(st.nextToken());
      V = Integer.parseInt(st.nextToken());
      testId++;
    }
    bw.flush();
  }
  static long answer(int L, int P, int V) {
    long numBundle = V/P;
    long remainder = V%P;
    return numBundle * L + Long.min(remainder, L);
  }
}
