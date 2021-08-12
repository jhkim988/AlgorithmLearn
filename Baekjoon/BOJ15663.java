import java.io.*;
import java.util.*;

public class BOJ15663 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int N;
  static int M;
  static int[] data;
  static boolean[] isUsed;
  // static ArrayList<HashSet<Integer>> duplCheck;
  // Need not to have HashSet. (ascending order)
  static int[] duplCheck;
  static int[] print;
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
    isUsed = new boolean[N];
    // duplCheck = new ArrayList<>();
    // for (int i = 0; i < M; i++) {
    //   duplCheck.add(new HashSet<>());
    // }
    duplCheck = new int[M];
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
    for (int i = 0; i < N; i++) {
      // if (!isUsed[i] && !duplCheck.get(depth).contains(data[i])) {
      if (!isUsed[i] && duplCheck[depth] != data[i]) {
        print[depth] = data[i];
        isUsed[i] = true;
        // duplCheck.get(depth).add(data[i]);
        duplCheck[depth] = data[i];
        backtracking(depth + 1);
        isUsed[i] = false;
        if (depth + 1 < M) {
          duplCheck[depth + 1] = 0;
        } 
      } 
    }
  }
}