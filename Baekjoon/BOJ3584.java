import java.io.*;
import java.util.*;

public class BOJ3584 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int n = Integer.parseInt(br.readLine());
      int[] tree = new int[n + 1]; // tree[a] = b: parent of a = b
      StringTokenizer st;
      for (int i = 0; i < n - 1; i++) {
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        tree[c] = p;
      }
      st = new StringTokenizer(br.readLine());
      int q1 = Integer.parseInt(st.nextToken());
      int q2 = Integer.parseInt(st.nextToken());
      boolean[] visit = new boolean[n + 1];
      while (tree[q1] != 0) {
        visit[q1] = true;
        q1 = tree[q1];
      }
      visit[q1] = true;
      while (!visit[q2]) {
        q2 = tree[q2];
      }
      bw.write(Integer.toString(q2));
      bw.newLine();
    }
    bw.flush();
  }
}
