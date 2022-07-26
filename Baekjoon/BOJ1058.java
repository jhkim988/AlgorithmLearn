import java.io.*;
import java.util.*;

public class BOJ1058 {
  static boolean[] visit;
  static Queue<Integer> que;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[][] rel = new char[n][n];
    for (int i = 0; i < n; i++) {
      rel[i] = br.readLine().toCharArray();
    }

    visit = new boolean[n];
    que = new LinkedList<>();

    int max = 0;
    for (int i = 0; i < n; i++) {
      int numFriend = bfs(rel, i);
      if (max < numFriend) max = numFriend;
    }
    bw.write(Integer.toString(max));
    bw.flush();
  }
  static int bfs(char[][] rel, int node) {
    Arrays.fill(visit, false);
    int num = 0;
    visit[node] = true;
    for (int i = 0; i < rel[node].length; i++) {
      if (rel[node][i] == 'N' || visit[i]) continue;
      visit[i] = true;
      que.add(i);
      num++;
    }
    while (!que.isEmpty()) {
      int crnt = que.poll();
      for (int i = 0; i < rel[crnt].length; i++) {
        if (rel[crnt][i] == 'N' || visit[i]) continue;
        visit[i] = true;
        num++;
      }
    }
    return num;
  }
}
