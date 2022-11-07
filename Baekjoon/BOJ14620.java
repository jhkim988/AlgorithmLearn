import java.io.*;
import java.util.*;

public class BOJ14620 {
    private static final long INF = Long.MAX_VALUE>>1;
    private static boolean[][] visit;
    private static int[] rowDi = {-1, 0, 1, 0, 0}, colDi = {0, -1, 0, 1, 0};
    private static int n;
    private static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][n];
        long answer = INF;
        for (int i = 0; i < n*n; i++) {
            answer = Long.min(answer, recur(0, i));
        }
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private static long recur(int depth, int pos) {
        if (depth >= 3) return 0;
        int row = pos/n;
        int col = pos%n;

        for (int k = 0; k < 5; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            if (isOutOfRange(adjRow, adjCol) || visit[adjRow][adjCol]) {
                return INF;
            }
        }

        long sumCost = 0;
        for (int k = 0; k < 5; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            sumCost += cost[adjRow][adjCol];
            visit[adjRow][adjCol] = true;
        }

        long min = INF;
        for (int i = 1; i + pos < n*n; i++) {
            min = Long.min(min, recur(depth + 1, pos + i));
        }
        visitFree(row, col);
        return min + sumCost;
    }

    private static boolean isOutOfRange(int row, int col) {
        return row < 0 || row >= n || col < 0 || col >= n;
    }

    private static void visitFree(int row, int col) {
        visit[row][col] = false;
        for (int k = 0; k < 4; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            if (isOutOfRange(adjRow, adjCol)) continue;
            visit[adjRow][adjCol] = false;
        }
    }
}
