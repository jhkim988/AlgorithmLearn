import java.io.*;
import java.util.*;

public class BOJ2309 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static final int num = 9;
  static final int targetNum = 7;
  static final int target = 100;
  static int[] data;
  static boolean isEnd = false;
  public static void main(String[] args) throws IOException {
    data = new int[num];
    for (int i = 0; i < num; i++) {
      data[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(data);
    boolean[] marked = new boolean[num];
    backtraking(0, 0, marked);
    bw.flush();
  }
  static void backtraking(int depth, int sum, boolean[] marked) throws IOException{
    if (isEnd) {
      return;
    }
    if (depth == targetNum) {
      if (sum == target) {
        for (int i = 0; i < num; i++) {
          if (marked[i]) {
            bw.write(data[i] + "\n");
          }
        }
        isEnd = true;
      }
      return;
    }
    for (int i = depth; i < num; i++) {
      if (marked[i]) continue;
      marked[i] = true;
      backtraking(depth + 1, sum + data[i], marked);
      marked[i] = false;
    }
  }
}