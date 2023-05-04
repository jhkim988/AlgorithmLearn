import java.io.*;
import java.util.*;

public class BOJ17829 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(Integer.toString(recur(arr, n)[0][0]));
        bw.flush();
    }

    private static int[][] recur(int[][] arr, int n) {
        if (n == 1) {
            return arr;
        }
        int half = n >> 1;
        int[][] newArr = new int[half][half];
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                int[] tmp = new int[4];
                tmp[0] = arr[i << 1][j << 1];
                tmp[1] = arr[(i << 1) + 1][j << 1];
                tmp[2] = arr[i << 1][(j << 1) + 1];
                tmp[3] = arr[(i << 1) + 1][(j << 1) + 1];
                Arrays.sort(tmp);
                newArr[i][j] = tmp[2];
            }
        }

        return recur(newArr, half);
    }
}