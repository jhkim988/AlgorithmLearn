import java.io.*;
import java.util.*;

public class BOJ2792 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int numStudent = Integer.parseInt(st.nextToken());
    int numKind = Integer.parseInt(st.nextToken());
    int[] jewerly = new int[numKind];
    for (int i = 0; i < numKind; i++) {
      jewerly[i] = Integer.parseInt(br.readLine());
    }
    int max = jewerly[0];
    for (int i = 0; i < numKind; i++) {
      max = Integer.max(max, jewerly[i]);
    }

    int lo = 0;
    int hi = max + 1;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid, numStudent, jewerly)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int val, int numStudent, int[] jewerly) {
    if (val == 0) return false;
    int numBundle = 0;
    for (int j : jewerly) {
      int add = j / val + (j % val == 0 ? 0 : 1);
      numBundle += add;
    }
    return numBundle <= numStudent;
  }
}
