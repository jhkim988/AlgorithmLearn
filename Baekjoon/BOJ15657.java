import java.io.*;
import java.util.*;

public class BOJ15657 {
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
    dfs(0, "", -1);
    bw.flush();
  }
  static void dfs(int depth, String str, int prev) throws IOException {
    if (depth >= M) {
      bw.write(str + "\n");
      return;
    }
    for (int i = 0; i < N; i++) {
      if (prev <= data[i]) {
        String sum = str + data[i] + " ";
        prev = data[i];
        dfs(depth + 1, sum, prev);
      } 
    }
  }
}
