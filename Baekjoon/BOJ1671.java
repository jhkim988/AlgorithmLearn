import java.io.*;
import java.util.*;

public class BOJ1671 {
  static boolean[] visit;
  static int[] predator, prey;
  static ArrayList<Queue<Integer>> graph;
  private static class Shark {
    int size, speed, intel;
    Shark(int size, int speed, int intel) {
      this.size = size;
      this.speed = speed;
      this.intel = intel;
    }
    int eatable(Shark other) {
      if (size == other.size && speed == other.speed && intel == other.intel) return 0;
      if (size >= other.size && speed >= other.speed && intel >= other.intel) return 1;
      return -1;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Shark[] shark = new Shark[n+1];
    for (int i = 1; i <= n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int size = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int intel = Integer.parseInt(st.nextToken());
      shark[i] = new Shark(size, speed, intel);
    }

    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new LinkedList<>());
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (i == j) continue;
        int compare = shark[i].eatable(shark[j]);
        if (compare == 1) graph.get(i).add(j);
        if (compare == 0) {
          int min = i > j ? j : i;
          int max = i > j ? i : j;
          graph.get(min).add(max);
        }
      }
    }

    int match = 0;
    visit = new boolean[n+1];
    predator = new int[n+1];
    prey = new int[n+1];
    for (int i = 1; i <= n; i++) {
      if (predator[i] != 0) continue;
      Arrays.fill(visit, false);
      if (dfs(i)) match++;
      Arrays.fill(visit, false);
      if (dfs(i)) match++;
    }
    bw.write(Integer.toString(n - match));
    bw.flush();
  }
  static boolean dfs(int i) {
    visit[i] = true;
    for (int j : graph.get(i)) {
      if (prey[j] == 0 || (!visit[prey[j]] && dfs(prey[j]))) {
        predator[i] = j;
        prey[j] = i;
        return true;
      }
    }
    return false;
  }
}
