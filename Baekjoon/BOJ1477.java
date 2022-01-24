import java.io.*;
import java.util.*;

public class BOJ1477 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int numRest = Integer.parseInt(st.nextToken());
    int addRest = Integer.parseInt(st.nextToken());
    int len = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] pos = new int[numRest + 2];
    pos[0] = 0; pos[numRest + 1] = len;
    for (int i = 1; i <= numRest; i++) pos[i] = Integer.parseInt(st.nextToken());
    Arrays.sort(pos);

    int maxGap = 0;
    int[] gap = new int[numRest + 1];
    for (int i = 0; i <= numRest; i++) {
      gap[i] = pos[i + 1] - pos[i];
      maxGap = Integer.max(maxGap, gap[i]);
    }

    int lo = 0;
    int hi = maxGap;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!possible(mid, gap, addRest)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean possible(int dist, int[] gap, int addRest) {
    if (dist <= 0) return false;
    int num = 0;
    for (int d : gap) {
      num += d / dist - (d % dist == 0 ? 1 : 0);
    }
    return num <= addRest;
  }
}
