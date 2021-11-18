import java.io.*;
import java.util.*;

public class BOJ16922 {
  static int N;
  static HashSet<Integer> hs;
  static int[] number = {1, 5, 10, 50};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    hs = new HashSet<>();

    recur(0, 0, 0);
    bw.write(hs.size() + "\n");
    bw.flush();
  } 
  static void recur(int depth, int numWrite, int val) {
    if (numWrite == N) {
      hs.add(val);
    }
    if (depth >= 4) {
      return;
    }
    for (int i = 0; i + numWrite <= N; i++) {
      recur(depth + 1, i + numWrite, val + i * number[depth]);
    }
  } 
}
