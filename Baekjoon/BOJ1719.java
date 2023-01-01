import java.io.*;
import java.util.*;

public class BOJ1719 {
    private static final int INF = Integer.MAX_VALUE >> 4;
    private static class Pair {
        int end, weight;
        Pair(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Pair(end, weight));
            graph.get(end).add(new Pair(start, weight));
        }

        int[][] dist = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }
        for (int i = 0; i <= n; i++) {
            for (Pair adj : graph.get(i)) {
                dist[i][adj.end] = adj.weight;
            }
            dist[i][i] = 0;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int[][] answer = new int[n+1][n+1];
        // for (int i = 0; i <= n; i++) Arrays.fill(answer[i], INF);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                for (Pair adj : graph.get(i)) {
                    int k = adj.end;
                    if (dist[i][j] == adj.weight + dist[k][j]) {
                        answer[i][j] = k;
                        break;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) bw.write('-');
                else bw.write(Integer.toString(answer[i][j]));
                bw.write(' ');
            }
            bw.newLine();
        }
        bw.flush();
    }
}