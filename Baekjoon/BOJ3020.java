import java.io.*;
import java.util.*;

public class BOJ3020 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());
    ArrayList<Integer> lower = new ArrayList<>(n/2);
    ArrayList<Integer> upper = new ArrayList<>(n/2);
    for (int i = 0; i < n; i++) {
      if ((i & 1) == 0) {
        lower.add(Integer.parseInt(br.readLine()));
      } else {
        upper.add(Integer.parseInt(br.readLine()));
      }
    }
    Collections.sort(lower, Collections.reverseOrder());
    Collections.sort(upper, Collections.reverseOrder());
    int min = n+1;
    int num = 0;
    for (int x = 1; x <= h; x++) {
      int vLower = numOfLower(x, lower);
      int vUpper = numOfLower(h+1-x, upper);
      int val = vLower + vUpper;
      if (val < min) {
        min = val;
        num = 1;
      } else if (val == min) {
        num++;
      }
    }
    bw.write(Integer.toString(min));
    bw.write(' ');
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
  static int numOfLower(int x, ArrayList<Integer> sorted) {
    int lo = -1, hi = sorted.size();
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (sorted.get(mid) >= x) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return hi;
  }
}
