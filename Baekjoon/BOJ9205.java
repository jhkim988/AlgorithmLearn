import java.io.*;
import java.util.*;

public class BOJ9205 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (this.getClass() != o.getClass()) return false;
      Pair p = (Pair) o;
      return p.row == this.row && p.col == this.col;
    }
    @Override
    public int hashCode() {
      int result = 7;
      result = result * 31 + row;
      result = result * 31 + col;
      return result;
    }
    public int dist(Pair other) {
      return (this.row<other.row ? other.row-this.row : this.row-other.row) + (this.col<other.col ? other.col-this.col : this.col-other.col);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      Pair start = new Pair(row, col);
      HashSet<Pair> convenient = new HashSet<>();
      for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        convenient.add(new Pair(row, col));
      }
      st = new StringTokenizer(br.readLine());
      row = Integer.parseInt(st.nextToken());
      col = Integer.parseInt(st.nextToken());
      Pair target = new Pair(row, col);
      convenient.add(target);
      boolean possible = bfs(start, target, convenient);
      bw.write(possible ? "happy\n" : "sad\n");
    }
    bw.flush();
  }
  static boolean bfs(Pair start, Pair target, HashSet<Pair> convenient) {
    Queue<Pair> que = new LinkedList<>();
    HashSet<Pair> visit = new HashSet<>();
    que.add(start);
    visit.add(start);
    while (!que.isEmpty()) {
      Pair crnt = que.poll();
      for (Pair adj : convenient) {
        if (visit.contains(adj)) continue;
        if (crnt.dist(adj) > 1_000) continue;
        if (adj.equals(target)) return true;
        visit.add(adj);
        que.add(adj);
      }
    }
    return false;
  }
}
