import java.io.*;
import java.util.*;

public class BOJ15658 {
  static int N;
  static int[] data;
  static int[] numOper;
  static int max = Integer.MIN_VALUE;
  static int min = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    numOper = new int[4];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 4; i++) {
      int input = Integer.parseInt(st.nextToken());
      numOper[i] = input;
    }
    recur(1, data[0]);
    bw.write(max + "\n");
    bw.write(min + "\n");
    bw.flush();
  }
  static void recur(int depth, int val) {
    if (depth == N) {
      if (max < val) {
        max = val;
      }
      if (val < min) {
        min = val;
      }
      return;
    }

    if (numOper[0] > 0) {
      numOper[0]--;
      recur(depth + 1, val + data[depth]);
      numOper[0]++;
    }
    if (numOper[1] > 0) {
      numOper[1]--;
      recur(depth + 1, val - data[depth]);
      numOper[1]++;
    }
    if (numOper[2] > 0) {
      numOper[2]--;
      recur(depth + 1, val * data[depth]);
      numOper[2]++;
    }
    if (numOper[3] > 0 && data[depth] != 0) {
      numOper[3]--;
      recur(depth + 1, val / data[depth]);
      numOper[3]++;
    }
  }
}
