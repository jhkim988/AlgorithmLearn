import java.io.*;
import java.util.*;

public class BOJ15724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nRow = Integer.parseInt(st.nextToken());
        int nCol = Integer.parseInt(st.nextToken());
        long[][] val = new long[nRow + 1][nCol + 1];
        for (int i = 1; i <= nRow; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= nCol; j++) {
                val[i][j] = val[i - 1][j] + val[i][j - 1] - val[i - 1][j - 1] + Long.parseLong(st.nextToken());
            }
        }

        int nQuery = Integer.parseInt(br.readLine());
        while (nQuery-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            bw.write(Long.toString(val[c][d] - val[c][b] - val[a][d] + val[a][b]));
            bw.newLine();
        }
        bw.flush();
    }
}
