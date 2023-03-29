import java.io.*;
import java.util.*;

public class BOJ5721 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nRow = 0;
        int nCol = 0;
        while ((nRow=Integer.parseInt(st.nextToken())) != 0 && (nCol=Integer.parseInt(st.nextToken())) != 0) {
            int[][] input = new int[nRow][nCol];
            for (int i = 0; i < nRow; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < nCol; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] dpRow = new int[nRow];
            int[][] dpCol = new int[nRow][nCol];

            for (int i = 0; i < nRow; i++) {
                dpCol[i][0] = input[i][0];
                for (int j = 1; j < nCol; j++) {
                    dpCol[i][j] = Integer.max(dpCol[i][j-1], (j >= 2 ? dpCol[i][j-2] : 0) + input[i][j]);
                }
            }

            dpRow[0] = dpCol[0][nCol-1];
            for (int i = 1; i < nRow; i++) {
                dpRow[i] = Integer.max(dpRow[i-1], (i >= 2 ? dpRow[i-2] : 0) + dpCol[i][nCol-1]);
            }

            bw.write(Integer.toString(dpRow[nRow-1]));
            bw.newLine();

            st = new StringTokenizer(br.readLine());
        }
        bw.flush();
    }
}