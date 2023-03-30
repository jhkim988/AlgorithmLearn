import java.io.*;
import java.util.*;

public class BOJ17265 {
    private static int n;
    private static final long MIN = -Long.MAX_VALUE >> 3;
    private static final long MAX = Long.MAX_VALUE >> 3;
    private static char[][] input;
    private static long[][] dpMax, dpMin;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        input = new char[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                input[i][j] = st.nextToken().charAt(0);
            }
        }

        dpMax = new long[n][n];
        dpMin = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dpMax[i], MIN);
            Arrays.fill(dpMin[i], MAX);
        }
        dpMax[0][0] = dpMin[0][0] = input[0][0] - '0';
        bw.write(Long.toString(recurMax(n-1, n-1)));
        bw.write(' ');
        bw.write(Long.toString(recurMin(n-1, n-1)));
        bw.flush();
    }

    private static long recurMax(int row, int col) {
        if (dpMax[row][col] != MIN) return dpMax[row][col];
        int val = input[row][col] - '0';
        long ret = MIN;
        if (row-2 >= 0) ret = Long.max(ret, calc(recurMax(row-2, col), input[row-1][col], val));
        if (row-1 >= 0 && col-1 >= 0) ret = Long.max(ret, calc(recurMax(row-1, col-1), input[row-1][col], val));
        if (row-1 >= 0 && col-1 >= 0) ret = Long.max(ret, calc(recurMax(row-1, col-1), input[row][col-1], val));
        if (col-2 >= 0) ret = Long.max(ret, calc(recurMax(row, col-2), input[row][col-1], val));
        return dpMax[row][col] = ret;
    }

    private static long recurMin(int row, int col) {
        if (dpMin[row][col] != MAX) return dpMin[row][col];
        int val = input[row][col] - '0';
        long ret = MAX;
        if (row-2 >= 0) ret = Long.min(ret, calc(recurMin(row-2, col), input[row-1][col], val));
        if (row-1 >= 0 && col-1 >= 0) ret = Long.min(ret, calc(recurMin(row-1, col-1), input[row-1][col], val));
        if (row-1 >= 0 && col-1 >= 0) ret = Long.min(ret, calc(recurMin(row-1, col-1), input[row][col-1], val));
        if (col-2 >= 0) ret = Long.min(ret, calc(recurMin(row, col-2), input[row][col-1], val));
        return dpMin[row][col] = ret;
    }

    private static long calc(long val1, char oper, long val2) {
        switch (oper) {
            case '+': return val1 + val2;
            case '-': return val1 - val2;
            default: return val1 * val2;
        }
    }
}
