import java.io.*;
import java.util.*;

public class BOJ1107 {
  static boolean[] broken;
  static ArrayList<Integer> candidate;
  static final int LIMIT = 1_000_000;
  static int min;
  static int target;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    target = Integer.parseInt(br.readLine());
    int numBroken = Integer.parseInt(br.readLine());
    broken = new boolean[10];
    for (int i = 0; i < 10; i++) {
      broken[i] = true;
    }
    if (numBroken > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < numBroken; i++) {
        broken[Integer.parseInt(st.nextToken())] = false;
      }
    }
    if (numBroken == 10) {
      bw.write(Math.abs(target - 100) + "\n");
      bw.flush();
      return;
    }
    min = Math.abs(target - 100);    
    backTracking(0, 0);
    bw.write(min + "\n");
    bw.flush();
  }
  static void backTracking(int sum, int depth) {
    if (depth > 7) {
      return;
    }
    if (sum > LIMIT) {
      return;
    }
    for (int i = 0; i <= 9; i++) {
      if (broken[i]) {
        sum = sum * 10 + i;
        int tmp = sum;
        int numClick = 0;
        while (tmp != 0) { 
          numClick++;
          tmp /= 10;
        }
        if (sum == 0) {
          numClick = 1;
        }
        int diff = Math.abs(sum - target);
        if (numClick + diff < min) {
          min = numClick + diff;
        }
        backTracking(sum, depth + 1);
        sum /= 10;
      }
    }
  }
}
