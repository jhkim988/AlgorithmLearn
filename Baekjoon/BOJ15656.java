import java.io.*;
import java.util.*;

public class BOJ15656 {
  static BufferedReader br;
  static BufferedWriter bw;
  static int N;
  static int M;
  static int[] data;
  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(data);
    int[] print = new int[M];
    recur(0, print);
    bw.flush();
  }
  static void recur(int depth, int[] print) throws IOException {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        bw.write(print[i] + " ");
      }
      bw.write('\n');
      return;
    }

    for (int i = 0; i < N; i++) {
      print[depth] = data[i];
      recur(depth + 1, print);
    }
  }
}
