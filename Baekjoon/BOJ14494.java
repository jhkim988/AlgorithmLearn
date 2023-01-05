import java.io.*;
import java.util.*;

public class BOJ14494 {
    private static final int d = 1_000_000_007;
    private static int nRow, nCol;
    private static final int[] rowDi = {0, -1, -1}, colDi = {-1, -1, 0};
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());
        dp = new int[nRow][nCol];
        dp[0][0] = 1;
        recur(nRow-1, nCol-1);
        
        bw.write(Integer.toString(dp[nRow-1][nCol-1]));
        bw.flush();
    }
    private static int recur(int row, int col) {
        if (dp[row][col] != 0) return dp[row][col];
        int sum = 0;
        for (int k = 0; k < 3; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) continue;
            sum = (sum + recur(adjRow, adjCol)) % d;
        }
        return dp[row][col] = sum;
    }
}