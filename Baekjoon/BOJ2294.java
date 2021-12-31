import java.io.*;
import java.util.*;

public class BOJ2294 {
  static final int MAX = 100_000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numKind = Integer.parseInt(st.nextToken());
    int target = Integer.parseInt(st.nextToken());
    int[] kind = new int[numKind];
    for (int i = 0; i < numKind; i++) kind[i] = Integer.parseInt(br.readLine());
    int[] list = new int[target + 1];
    Arrays.fill(list, MAX);
    for (int i = 0; i < numKind; i++) {
      if (kind[i] > target) continue;
      list[kind[i]] = 1;
    }
    for (int i = 0; i <= target; i++) {
      for (int j = 0; j < numKind; j++) {
        if (i + kind[j] > target) continue;
        list[i + kind[j]] = Math.min(list[i + kind[j]], list[i] + 1);
      }
    }
    bw.write(list[target] == MAX ? "-1\n" : list[target] + "\n");
    bw.flush();
  }
}
