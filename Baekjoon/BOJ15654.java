import java.io.*;
import java.util.*;

public class BOJ15654 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int N;
  static int M;
  static int[] data;
  public static void main(String[] args) throws IOException {


    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(data);
    boolean[] check = new boolean[N];
    dfs(check, 0, "");
    bw.flush();
  }
  static void dfs(boolean[] check, int depth, String str) throws IOException {
    if (depth >= M) {
      bw.write(str + "\n");
      return;
    }
    for (int i = 0; i < N; i++) {
      if (!check[i]) {
        String sum = str + data[i] + " ";
        check[i] = true;
        dfs(check, depth + 1, sum);
        check[i] = false;
      }
    }
  }
}
