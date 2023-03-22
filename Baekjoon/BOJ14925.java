import java.io.*;
import java.util.*;

public class BOJ14925 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nRow = Integer.parseInt(st.nextToken());
        int nCol = Integer.parseInt(st.nextToken());
        int[][] input = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nCol; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] rowDi = { 1, 1, 0 }, colDi = { 0, 1, 1 };

        int max = 0;
        int[][] dp = new int[nRow][nCol];
        for (int i = nRow - 1; i >= 0; i--) {
            for (int j = nCol - 1; j >= 0; j--) {
                if (input[i][j] != 0) {
                    dp[i][j] = 0;
                } else {
                    int min = 1001;
                    for (int k = 0; k < 3; k++) {
                        int adjRow = i + rowDi[k];
                        int adjCol = j + colDi[k];
                        if (adjRow >= nRow || adjCol >= nCol) {
                            min = Integer.min(min, 0);
                        } else {
                            min = Integer.min(min, dp[adjRow][adjCol]);
                        }
                    }
                    dp[i][j] = min == 1001 ? 1 : min+1;
                    max = Integer.max(max, dp[i][j]);
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
