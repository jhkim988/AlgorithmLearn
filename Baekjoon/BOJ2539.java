import java.io.*;
import java.util.*;

public class BOJ2539 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    int lim = Integer.parseInt(br.readLine().trim());
    int numWrong = Integer.parseInt(br.readLine().trim());
    boolean[] info = new boolean[numCol];
    int max = 0;
    for (int i = 0; i < numWrong; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      info[c] = true;
      max = Integer.max(max, r + 1);
    }

    int lo = max - 1;
    int hi = numCol;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid, info, lim, max)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int size, boolean[] info, int lim, int max) {
    if (size < max) return false;
    int count = 0;
    for (int c = 0; c < info.length; c++) {
      if (info[c]) {
        c += size - 1;
        count++;
      }
    }
    return count <= lim;
  }
}
