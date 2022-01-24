import java.io.*;
import java.util.*;

public class BOJ2512 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numRegion = Integer.parseInt(br.readLine());
    int[] budgetReq = new int[numRegion];
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < numRegion; i++) budgetReq[i] = Integer.parseInt(st.nextToken());
    int total = Integer.parseInt(br.readLine());

    // find limit
    int lo = 0;
    int hi = total + 1;
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (check(mid, budgetReq, total)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }

    bw.write(Integer.toString(lo));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int lim, int[] budgetReq, int total) {
    boolean flag = false;
    int sum = 0;
    for (int budget : budgetReq) {
      if (budget < lim) {
        sum += budget;
      } else {
        sum += lim;
        flag = true;
      }
    }
  return flag ? sum <= total : false;
  }
}
