import java.io.*;

public class BOJ10974 {
  static  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N;
  static boolean[] marked;
  static int[] print;
  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(br.readLine());
    marked = new boolean[N];
    print = new int[N];
    backtraking(0);
    bw.flush();
  } 
  static void backtraking(int depth) throws IOException {
    if (depth == N) {
      for (int i = 0; i < N; i++) {
        bw.write(print[i] + " ");
      }
      bw.write("\n");
      return;
    }
    for (int i = 0; i < N; i++) {
      if (marked[i]) continue;
      marked[i] = true;
      print[depth] = i + 1;
      backtraking(depth + 1);
      marked[i] = false;
    }
  } 
}
