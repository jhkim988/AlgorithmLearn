import java.io.*;
import java.util.*;

public class BOJ1188 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    bw.write(Integer.toString(minCut(n, m)));
    bw.flush();
  }
  static int minCut(int n, int m) {
    n %= m;
    int total = n*m;
    int numCut = 0;
    for (int ptr = 0; ptr < total; ptr += n) {
      if (ptr % m != 0) numCut++;
    }
    return numCut;
  }
}
