import java.io.*;
import java.util.*;

public class BOJ3980 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            int[][] inputs = new int[11][11];
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    inputs[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean[] check = new boolean[11];
            int answer = recur(inputs, check, 0);

            bw.write(Integer.toString(answer));
            bw.newLine();
        }
        bw.flush();
    }

    private static int recur(int[][] arr, boolean[] check, int depth) {
        if (depth >= check.length) return 0;
        int ret = 0;
        for (int i = 0; i < arr[depth].length; i++) {
            if (arr[depth][i] == 0 || check[i]) continue;
            check[i] = true;
            ret = Integer.max(ret, recur(arr, check, depth+1) + arr[depth][i]);
            check[i] = false;
        }
        return ret <= 0 ? -10_00_000 : ret;
    }
}
