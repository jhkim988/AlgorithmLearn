import java.io.*;
import java.util.*;

public class BOJ2829 {
    private static int[][] primDiag, otherDiag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine().trim());
        int[][] arr = new int[n+2][n+2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        primDiag = new int[n+2][n+2];
        otherDiag = new int[n+2][n+2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                primDiag[i][j] = primDiag[i-1][j-1] + arr[i][j];
                otherDiag[i][j] = otherDiag[i-1][j+1] + arr[i][j];
            }
        }

        int max = 0;
        for (int startRow = 1; startRow <= n; startRow++) {
            for (int startCol = 1; startCol <= n; startCol++) {
                for (int subMatSize = 1; startRow+subMatSize <= n && startCol+subMatSize <= n; subMatSize++) {
                    int endRow = startRow + subMatSize;
                    int endCol = startCol + subMatSize;
                    int val = calcBeauty(startRow, startCol, endRow, endCol);
                    if (max < val) max = val;
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
        bw.flush();
    }

    private static int calcBeauty(int startRow, int startCol, int endRow, int endCol) {
        return primDiag[endRow][endCol]-primDiag[startRow-1][startCol-1] - (otherDiag[endRow][startCol]-otherDiag[startRow-1][endCol+1]);    
    }
}