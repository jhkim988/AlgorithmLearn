import java.io.*;
import java.util.*;

public class BOJ4963 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int w = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());
    while (w != 0 && h != 0) {
      int[][] map = new int[h][w];
      for (int i = 0; i < w; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < h; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int numIsland = useBFS(map);
      // int numIsland = useUF(map);
      bw.write(numIsland + "\n");

      st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
  static int useBFS(int[][] map) {
    int numIsland = 0;
    int h = map.length;
    int w = map[0].length;
    boolean[][] marked = new boolean[h][w];

    return numIsland;
  }
}
