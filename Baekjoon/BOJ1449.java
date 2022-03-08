import java.io.*;
import java.util.*;

public class BOJ1449 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    int[] pos = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      pos[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(pos);
    int x = pos[0];
    int num = 0;
    while (true) {
      // System.out.println("[" + x + ", " + (x+l-1) + "]");
      int idx = find(x + l - 1, pos);
      // System.out.println("idx: " + idx);
      num++;
      if (idx >= n) break;
      int next = idx + (pos[idx] == (x+l-1) ? 1 : 0);
      if (next >= n || x+l-1 > pos[n-1]) break;
      x = pos[next];
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.flush();
  }
  static int find(int key, int[] arr) {
    int lo = -1;
    int hi = arr.length;
    while (lo + 1 < hi) {
      int mid = (lo + hi) >> 1;
      if (arr[mid] < key) lo = mid;
      else hi = mid;
    }
    return hi;
  }
}
