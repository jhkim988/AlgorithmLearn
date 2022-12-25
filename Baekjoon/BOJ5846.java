import java.io.*;
import java.util.*;

public class BOJ5846 {
    private static int n;
    private static int[][] height;
    private static int[][] cache;
    private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        height = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cache = new int[n][n];
        int lo = -1, hi = 1_000_001;
        while (lo+1 < hi) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(cache[i], 0);
            }

            int mid = (lo + hi) >> 1;
            if (possibleHalfTravel(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        bw.write(Integer.toString(hi));
        bw.flush();
    }

    private static boolean possibleHalfTravel(int cost) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cache[i][j] != 0) continue;
                if (dfs(i, j, cost)*2 > n*n) return true; 
            }
        }
        return false;
    }

    private static int dfs(int row, int col, int cost) {
        int count = 1;
        for (int k = 0; k < 4; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            if (!inRange(adjRow, adjCol)) continue;
            if (cache[adjRow][adjCol] != 0 || abs(height[row][col] - height[adjRow][adjCol]) > cost) continue;
            cache[adjRow][adjCol] = 1;
            count += dfs(adjRow, adjCol, cost);
        }
        return cache[row][col] = count;
    }

    private static boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }

    private static int abs(int x) {
        return x >= 0 ? x : -x;
    }
}