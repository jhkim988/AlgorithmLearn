import java.io.*;

public class BOJ1248 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    char[][] sign = new char[N][N];
    String input = br.readLine();
    int ptr = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < i; j++) {
        sign[i][j] = 'x'; // dummy
      }
      for (int j = i; j < N; j++) {
        sign[i][j] = input.charAt(ptr);
        ptr++;
      }
    }
    bw.write(answer(N, sign));
    bw.flush();
  }
  static String answer(int N, char[][] sign) {
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
  static void recur(int depth, int N, char[][] sign, int[] result, boolean[] flag) {
    if (depth == N) {
      flag[0] = true;
      return;
    }
    if (sign[depth][depth] == '+') {
      recur: for (int i = 1; i <= 10; i++) {
        result[depth] = i;
        int sum = 0;
        int ptr = depth;
        for (int j = depth; j >= 0; j--) {
          sum += result[j];
          if (sign[ptr][depth] == '+') {
            if (sum <= 0) continue recur;
          } else if (sign[ptr][depth] == '-') {
            if (sum >= 0) continue recur;
          } else {
            if (sum != 0) continue recur;
          }
          ptr--;
        }
        recur(depth + 1, N, sign, result, flag);
        if (flag[0]) return;
      }
    } else if (sign[depth][depth] == '-') {
      recur: for (int i = -1; i >= -10; i--) {
        result[depth] = i;
        int sum = 0;
        int ptr = depth;
        for (int j = depth; j >= 0; j--) {
          sum += result[j];
          if (sign[ptr][depth] == '+') {
            if (sum <= 0) continue recur;
          } else if (sign[ptr][depth] == '-') {
            if (sum >= 0) continue recur;
          } else {
            if (sum != 0) continue recur;
          }
          ptr--;
        }
        recur(depth + 1, N, sign, result, flag);
        if (flag[0]) return;
      }
    } else {
      result[depth] = 0;
      int sum = 0;
      int ptr = depth;
      for (int j = depth; j >= 0; j--) {
        sum += result[j];
        if (sign[ptr][depth] == '+') {
          if (sum <= 0) return;
        } else if (sign[ptr][depth] == '-') {
          if (sum >= 0) return;
        } else {
          if (sum != 0) return;
        }
        ptr--;
      }
      recur(depth + 1, N, sign, result, flag);
      if (flag[0]) return;
      }
    }
}
