import java.io.*;
import java.util.*;

public class BOJ6236 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int day = Integer.parseInt(st.nextToken());
    int withdraw = Integer.parseInt(st.nextToken());
    int[] budget = new int[day];
    int total = 0;
    for (int i = 0; i < day; i++) {
      total += budget[i] = Integer.parseInt(br.readLine());
    }

    int lo = 0;
    int hi = total;

    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid, withdraw, budget)) lo = mid;
      else hi = mid;
    }

    bw.write(Integer.toString(hi));
    bw.newLine();
    bw.flush();
  }
  static boolean check(int val, int widthdraw, int[] budget) {
    int count = 1;
    int sum = 0;
    for (int m : budget) {
      if (val < m) return false;
      if (sum + m > val) {
        sum = m;
        count++;
      } else {
        sum += m;
      }
    }
    return count <= widthdraw;
  } 
}
