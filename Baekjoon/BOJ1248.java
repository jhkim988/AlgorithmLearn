import java.io.*;

public class BOJ1248 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    char[] sign = br.readLine().toCharArray();
    bw.write(answer(N, sign));
    bw.flush();
  }
  static String answer(int N, char[] sign) {
    int[] result = new int[N];
    boolean[] flag = {false};
    recur(0, N, sign, result, flag);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      sb.append(result[i]).append(' ');
    }
    sb.append('\n');
    return sb.toString();
  }
  static void recur(int depth, int N, char[] sign, int[] result, boolean[] flag) {
    if (depth == N) {
      flag[0] = true;
      return;
    }
    int checkIdx = depth * N - (depth - 1);
    if (sign[checkIdx] == '+') {
      recur: for (int i = 1; i <= 10; i++) {
        result[depth] = i;
        int sum = 0;
        for (int j = depth; j >= 0; j--) {
          sum += result[j];
          if (sign[checkIdx - (N - depth - j - 1)] == '+') {
            if (sum <= 0) continue recur;
          } else if (sign[checkIdx - (N - depth - j)] == '-') {
            if (sum >= 0) continue recur;
          } else {
            if (sum != 0) continue recur;
          }
        }
        recur(depth + 1, N, sign, result, flag);
        if (flag[0]) return;
      }
    } else if (sign[checkIdx] == '-') {
      recur: for (int i = -1; i >= -10; i--) {
        result[depth] = i;
        int sum = 0;
        for (int j = depth; j >= 0; j--) {
          sum += result[j];
          if (sign[checkIdx - (N - depth - j - 1)] == '+') {
            if (sum <= 0) continue recur;
          } else if (sign[checkIdx - (N - depth - j)] == '-') {
            if (sum >= 0) continue recur;
          } else {
            if (sum != 0) continue recur;
          }
        }
        recur(depth + 1, N, sign, result, flag);
        if (flag[0]) return;
      }
    } else {
      result[depth] = 0;
      int sum = 0;
      for (int j = depth; j >= 0; j--) {
        sum += result[j];
        if (sign[checkIdx - (N - depth - j - 1)] == '+') {
          if (sum <= 0) return;
        } else if (sign[checkIdx - (N - depth - j - 1)] == '-') {
          if (sum >= 0) return;
        } else {
          if (sum != 0) return;
        }
        recur(depth + 1, N, sign, result, flag);
        if (flag[0]) return;
      }
    }
  }
}
