import java.io.*;
import java.util.*;

public class BOJ2186 {
    private static int nRow, nCol, k;
    private static char[][] board;
    private static char[] key;
    private static int[] rowDi = {-1, 0, 1, 0}, colDi = {0, -1, 0, 1};
    private static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new char[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            board[i] = br.readLine().toCharArray();
        }
        key = br.readLine().toCharArray();
        dp = new int[key.length][nRow][nCol];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < nRow; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int answer = 0;
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                if (board[i][j] == key[0]) answer += recur(0, i, j);
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
    private static int recur(int depth, int row, int col) {
        if (depth+1 >= key.length) return 1;
        if (dp[depth][row][col] != -1) return dp[depth][row][col];
        int ret = 0;
        for (int direction = 0; direction < 4; direction++) {
            for (int dist = 1; dist <= k; dist++) {
                int adjRow = row + rowDi[direction]*dist;
                int adjCol = col + colDi[direction]*dist;
                if (adjRow < 0 || adjRow >= nRow || adjCol < 0 | adjCol >= nCol) continue;
                if (board[adjRow][adjCol] != key[depth+1]) continue;
                ret += recur(depth+1, adjRow, adjCol);
            }
        }
        return dp[depth][row][col] = ret;
    }
}