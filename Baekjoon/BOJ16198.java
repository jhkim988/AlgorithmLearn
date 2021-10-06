import java.io.*;
import java.util.*;

public class BOJ16198 {
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    LinkedList<Integer> data = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      data.add(Integer.parseInt(st.nextToken()));
    }

    bw.write(answer(data) + "\n");
    bw.flush();
  }
  static int answer(LinkedList<Integer> data) {
    return recur(0, 0, data);
  }
  static int recur(int depth, int sum, LinkedList<Integer> data) {
    if (depth >= N - 2) {
      return sum;
    }
    int max = 0;
    for (int i = 1; i < data.size() - 1; i++) {
      int tmp1 = data.get(i - 1);
      int tmp2 = data.get(i + 1);
      int del = data.remove(i);
      max = Math.max(max, recur(depth + 1, sum + tmp1 * tmp2, data));
      data.add(i, del);
    }
    return max;
  }
}
