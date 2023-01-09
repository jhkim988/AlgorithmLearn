import java.io.*;
import java.util.*;

public class BOJ14430 {
    private static int nRow, nCol;
    private static int[] rowDi = {-1, 0}, colDi = {0, -1};
    private static int[][] board, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());
        board = new int[nRow][nCol];
        dp = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nCol; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        bw.write(Integer.toString(recur(nRow-1, nCol-1)));
        bw.flush();
    }

    private static int recur(int row, int col) {
        if (dp[row][col] != -1) return dp[row][col];
        int ret = 0;
        for (int k = 0; k < 2; k++) {
            int adjRow = row + rowDi[k];
            int adjCol = col + colDi[k];
            if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) continue;
            ret = Integer.max(ret, recur(adjRow, adjCol));
        }
        return dp[row][col] = ret + board[row][col];
    }
}
