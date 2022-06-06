import java.io.*;
import java.util.*;

public class BOJ1326 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken())-1;
    int end = Integer.parseInt(st.nextToken())-1;
    int[] dist = new int[n];
    Arrays.fill(dist, -1);
    Queue<Integer> que = new LinkedList<>();
    que.add(start); dist[start] = 0;
    int[] move = {1, -1};
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int k = 0; k < 2; k++) {
        int next = crnt + arr[crnt]*move[k];
        while (0 <= next && next < n) {
          if (dist[next] == -1) {
            dist[next] = dist[crnt]+1;
            if (next == end) {
              bw.write(Integer.toString(dist[next]));
              bw.flush();
              return;
            }
            que.add(next);
          }
          next += arr[crnt]*move[k];
        }
      }
    }
    bw.write("-1\n");
    bw.flush();
  }
}
