import java.io.*;
import java.util.*;

public class BOJ14225 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    bw.write(answer(data) + "\n");
    bw.flush();
  }
  static int answer(int[] data) {
    Arrays.sort(data);
    int sum = 0;
    for (int el : data) {
      sum += el; // sum <= 20_000_000;
    }
    boolean[] check = new boolean[sum + 1];
    // boolean[] marked = new boolean[data.length];
    recur(0, 0, data, check);
    for (int i = 1; i <= sum; i++) {
      if (check[i] == false) return i;
    }
    return sum + 1;
  }
  static void timeLimitExceed(int depth, int sum, int[] data, boolean[] marked, boolean[] check) {
    // 순서까지 고려된 재귀, 순서는 필요 없다.
    if (depth >= data.length) {
      return;
    }
    
    for (int i = depth; i < data.length; i++) {
      if (marked[i]) continue;
      marked[i] = true;
      check[sum + data[i]] = true;
      recur(depth + 1, sum + data[i], data, marked, check);
      marked[i] = false;
    }
  }

  static void recur(int depth, int sum, int[] data, boolean[] check) {
    if (depth >= data.length) {
      return;
    }
    recur(depth + 1, sum, data, check);
    check[sum + data[depth]] = true;
    recur(depth + 1, sum + data[depth], data, check);
  }
}
