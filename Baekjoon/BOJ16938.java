import java.io.*;
import java.util.*;

public class BOJ16938 {
  static int count = 0;
  static int N;
  static int L;
  static int R;
  static int X;
  static int[] data;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());
    data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    recur(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

    bw.write(count + "\n");
    bw.flush();
  } 
  static void recur(int depth, int numChoose, int min, int max, int sum) {
    // System.out.println("call: depth: " + depth + " / numChoose: " + numChoose + " / min: " + min + " / max: " + max + " / sum: " + sum);
    if (depth >= N) return;
    int newMin = Math.min(min, data[depth]);
    int newMax = Math.max(max, data[depth]);
    recur(depth + 1, numChoose, min, max, sum); // not choose
    if (sum + data[depth] <= R) { 
      int val = sum + data[depth];
      if (numChoose >= 1 && L <= val && val <= R && newMax - newMin >= X) {
        count++;
        // System.out.println("COUNT!");
      }
      recur(depth + 1, numChoose + 1, newMin, newMax, sum + data[depth]); // choose
    }
  }
}
