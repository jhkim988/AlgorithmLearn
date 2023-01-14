import java.io.*;
import java.util.*;

public class BOJ17090 {
    private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, 1, 0, -1};
    private static int nRow, nCol;
    private static char[][] board;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());
        board = new char[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            board[i] = br.readLine().toCharArray();
        }
        dp = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = 0;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                answer += recur(i, j);
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int row, int col) {
        if (dp[row][col] != -1) return dp[row][col];
        dp[row][col] = 0;
        int k = directionIndex(board[row][col]);
        int adjRow = row + rowDi[k];
        int adjCol = col + colDi[k];
        if (adjRow < 0 || adjRow >= nRow || adjCol < 0 || adjCol >= nCol) {
            return dp[row][col] = 1;
        }
        return dp[row][col] = recur(adjRow, adjCol);
    }
    private static int directionIndex(char ch) {
        if (ch == 'U') return 0;
        else if (ch == 'R') return 1;
        else if (ch == 'D') return 2;
        return 3;
    }
}