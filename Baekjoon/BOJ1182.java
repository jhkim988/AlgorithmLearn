import java.io.*;
import java.util.*;

public class BOJ1182 {
  static int N;
  static int S;
  static int[] data;
  static long count;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());
    data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    backtraking(0, 0L, false);
    bw.write(count + "\n");
    bw.flush();
  }
  static void backtraking(int index, long sum, boolean picked) {
    if (picked && index == N && sum == S) {
      count++;
    }
    if (index >= N) return;
    backtraking(index + 1, sum + data[index], true);
    backtraking(index + 1, sum, picked);
  }
}