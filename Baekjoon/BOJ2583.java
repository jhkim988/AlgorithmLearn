import java.io.*;
import java.util.*;

public class BOJ2583 {
  private static class Pair {
    int row, col;
    Pair(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
  public static void main(String[] args) throws IOException {
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    boolean[][] stat = new boolean[m][n];
    while (k-- > 0) {
      st = new StringTokenizer(br.readLine());
      int dx = Integer.parseInt(st.nextToken());
      int dy = m - 1 - Integer.parseInt(st.nextToken());
      int ux = Integer.parseInt(st.nextToken()) - 1;
      int uy = m - Integer.parseInt(st.nextToken());
      for (int i = uy; i <= dy; i++) {
        for (int j = dx; j <= ux; j++) {
          stat[i][j] = true;
        }
      }
    }

    int num = 0;
    ArrayList<Integer> areaList = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (stat[i][j]) continue;
        int area = 1;
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(i, j));
        stat[i][j] = true;
        num++;
        while (!que.isEmpty()) {
          Pair crnt = que.poll();
          for (int l = 0; l < 4; l++) {
            int adjRow = crnt.row + rowDi[l];
            int adjCol = crnt.col + colDi[l];
            if (adjRow < 0 || adjRow >= m || adjCol < 0 || adjCol >= n) continue;
            if (stat[adjRow][adjCol]) continue;
            area++;
            stat[adjRow][adjCol] = true;
            que.add(new Pair(adjRow, adjCol));
          }
        }
        areaList.add(area);
      }
    }
    Collections.sort(areaList);
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.write(Integer.toString(areaList.get(0)));
    for (int i = 1; i < areaList.size(); i++) {
      bw.write(' ');
      bw.write(Integer.toString(areaList.get(i)));
    }
    bw.newLine();
    bw.flush();
  }
}
