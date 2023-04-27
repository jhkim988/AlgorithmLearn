import java.io.*;
import java.util.*;

public class BOJ21278 {
    private static final int INF = Integer.MAX_VALUE >> 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Queue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[][] dist = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        // Floyd-Warshall
        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
            for (int j : graph.get(i)) {
                dist[i][j] = 1;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    dist[j][k] = Integer.min(dist[j][k], + dist[j][i] + dist[i][k]);
                }
            }
        }

        int minTime = INF;
        int v1 = 0, v2 = 0;
        for (int i = 1; i <= n-1; i++) {
            for (int j = i+1; j <= n; j++) {
                int time = 0;
                for (int k = 1; k <= n; k++) {
                    time += Integer.min(dist[i][k], dist[j][k]);
                }

                if (time < minTime) {
                    minTime = time;
                    v1 = i;
                    v2 = j;
                }
            }
        }

        bw.write(Integer.toString(v1));
        bw.write(' ');
        bw.write(Integer.toString(v2));
        bw.write(' ');
        bw.write(Integer.toString(minTime*2));
        bw.flush();
    }
}
