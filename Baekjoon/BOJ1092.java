import java.io.*;
import java.util.*;

public class BOJ1092 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] limit = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      limit[i] = Integer.parseInt(st.nextToken());
    }
    int m = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    int[] weight = new int[m];
    for (int i = 0; i < m; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(limit);
    Arrays.sort(weight);
    int[] index = new int[n];
    for (int i = 0; i < n; i++) {
      index[i] = binSearch(weight, limit[i]);
    }
    if (index[n-1] != m-1) {
      bw.write("-1");
      bw.flush();
      return;
    }
    int time = 0;
    boolean[] visit = new boolean[m];
    while (true) {
      boolean flag = false;
      for (int i = 0; i < n; i++) {
        while (index[i] >= 0 && visit[index[i]]) index[i]--;
        if (index[i] < 0) continue;
        visit[index[i]] = true;
        index[i]--;
        flag = true;
      }
      if (!flag) break;
      time++;
    }
    bw.write(Integer.toString(time));
    bw.flush();
  }
  static int binSearch(int[] arr, int key) {
    int lo = 0, hi = arr.length;
    while (lo+1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] <= key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
}
