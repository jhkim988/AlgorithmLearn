import java.io.*;
import java.util.*;

public class BOJ15665 {
  static BufferedReader br;
  static BufferedWriter bw;
  static int N;
  static int M;
  static int[] data;
  static int[] print;
  static HashSet<String> hs;
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
    print = new int[M];
    hs = new HashSet<>();
    Arrays.sort(data);
    recur(0);
    bw.flush();
  }
  static void recur(int depth) throws IOException {
    if (depth == M) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < M; i++) {
        sb.append(print[i]).append(' ');
      }
      sb.append('\n');
      if (!hs.contains(sb.toString())) {
        hs.add(sb.toString());
        bw.write(sb.toString());
      }
      return;
    }

    for (int i = 0; i < N; i++) {
      print[depth] = data[i];
      recur(depth + 1);
    }
  }
}
