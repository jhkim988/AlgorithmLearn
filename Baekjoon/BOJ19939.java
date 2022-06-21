import java.io.*;
import java.util.*;

public class BOJ19939 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    bw.write(Integer.toString(answer(n, k)));
    bw.flush();
  }
  static int answer(int n, int k) {
    if (2*n < k*(k+1)) return -1;
    if ((n-k*(k-1)/2) % k == 0) return k-1;
    return k;
  }
}
