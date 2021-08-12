import java.io.*;
import java.util.*;

public class BOJ15666 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int N;
  static int M;
  static TreeSet<Integer> data;
  static int[] print;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    data = new TreeSet<>();
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data.add(Integer.parseInt(st.nextToken()));
    }
    print = new int[M];
    backtracking(0);
  }
  static void backtracking(int depth) throws IOException {
    if (depth == M) {
      for (int i = 0; i < M - 1; i++) {
        bw.write(print[i] + " ");
      }
      bw.write(print[M - 1] + "\n");
      bw.flush();
      return;
    }
    for (int el : data) {
      if (depth == 0 || print[depth - 1] <= el) {
        print[depth] = el;
        backtracking(depth + 1);
      } 
    }
  }
}