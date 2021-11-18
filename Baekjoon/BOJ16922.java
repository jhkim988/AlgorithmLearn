import java.io.*;
import java.util.*;

public class BOJ16922 {
  static int N;
  static HashSet<Integer> hs;
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
    switch (depth) {
      case 0:
        for (int i = 0; i <= N; i++) {
          recur(depth + 1, i, i);
        }
        break;
      case 1:
        for (int i = 0; i + numWrite <= N; i++) {
          recur(depth + 1, i + numWrite, val + i * 5);
        }
        break;
      case 2:
        for (int i = 0; i + numWrite <= N; i++) {
          recur(depth + 1, i + numWrite, val + i * 10);
        }
        break;
      case 3:
        for (int i = 0; i + numWrite <= N; i++) {
          recur(depth + 1, i + numWrite, val + i * 50);
        }
    }
    if (numWrite == N) {
      hs.add(val);
    }
  } 
}
