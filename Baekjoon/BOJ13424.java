import java.io.*;
import java.util.*;

public class BOJ13424 {
    private static int INF = Integer.MAX_VALUE >> 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] dist = new int[n+1][n+1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                dist[start][end] = Integer.min(dist[start][end], weight);
                dist[end][start] = Integer.min(dist[start][end], weight);
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            int nFriend = Integer.parseInt(br.readLine());
            Queue<Integer> friends = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            while (nFriend-- > 0) {
                friends.add(Integer.parseInt(st.nextToken()));
            }

            int minIdx = 1;
            int minVal = INF;
            for (int i = 1; i <= n; i++) {
                int sum = 0;
                for (int friend : friends) {
                    sum += dist[i][friend];
                }
                if (sum < minVal) {
                    minVal = sum;
                    minIdx = i;
                }
            }

            bw.write(Integer.toString(minIdx));
            bw.newLine();
        }
        bw.flush();
    }
}