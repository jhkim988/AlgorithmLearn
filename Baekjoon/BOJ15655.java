import java.io.*;
import java.util.*;

public class BOJ15655 {
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

    st = new StringTokenizer(br.readLine());
    data = new int[N];
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(data);
    int[] print = new int[M];
    recur(0, 0, print);
    bw.flush();
  } 
  static void recur(int prev, int depth, int[] print) throws IOException {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        bw.write(print[i] + " ");
      }
      bw.write("\n");
      return;
    }
    for (int i = prev; i < N; i++) {
      print[depth] = data[i];
      recur(i + 1, depth + 1, print);
      print[depth] = 0;
    }
  } 
}
