import java.io.*;
import java.util.*;

public class BOJ1025 {
    private static int n, m;
    private static long[][] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        table = new long[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                table[i][j] = input[j] - '0';
            }
        }

        long max = -1;
        for (int rowDiff = -n; rowDiff < n; rowDiff++) {
            for (int colDiff = -m; colDiff < m; colDiff++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (isSquare(table[i][j])) max = Long.max(max, table[i][j]);
                        if (rowDiff == 0 && colDiff == 0) continue;
                        max = Long.max(max, recur(i, j, rowDiff, colDiff, 0));
                    }
                }
            }
        }

        bw.write(Long.toString(max));
        bw.flush();
    }
    private static long recur(int row, int col, int rowDiff, int colDiff, long acc) {
        if (row < 0 || row >= n || col < 0 || col >= m) return -1;
        long val = acc * 10 + table[row][col];
        long next = recur(row + rowDiff, col + colDiff, rowDiff, colDiff, val);
        if (!isSquare(val)) val = -1;
        return Long.max(val, next);
    }
    private static boolean isSquare(long val) {
        long lo = -1, hi = val+1;
        while (lo + 1 < hi) {
            long mid = (lo + hi) >> 1;
            if (mid*mid < val) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi*hi == val;
    }
}