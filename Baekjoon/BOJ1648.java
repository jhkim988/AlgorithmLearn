import java.io.*;
import java.util.*;

public class BOJ1648 {
    private static int d = 9901;
    private static int nRow, nCol;
    private static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nRow = Integer.parseInt(st.nextToken());
        nCol = Integer.parseInt(st.nextToken());
        dp = new int[nRow][nCol][1 << 15];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        bw.write(Integer.toString(recur(0, 0, 0)));
        bw.flush();
    }
    private static int recur(int row, int col, int bit) {
        if ((bit & (1 << col)) != 0) {
            bit &= ~(1 << col);
            return recur(row + (col+1)/nCol, (col+1)%nCol, bit);
        }
        if (row >= nRow) return 1;
        if (dp[row][col][bit] != -1) return dp[row][col][bit];
        int ret = 0;
        // 1. horizontal
        if (col+1 < nCol && (bit & (1 << (col+1))) == 0) {
            ret += recur(row + (col+2)/nCol, (col+2) % nCol, bit);
        }

        // 2. vertical
        if (row+1 < nRow) {
            bit |= 1 << col;
            ret += recur(row + (col+1)/nCol, (col+1)%nCol, bit);
            bit &= ~(1 << col);
        }
        return dp[row][col][bit] = ret % d;
    }
}
