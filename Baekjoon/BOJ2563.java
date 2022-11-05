import java.io.*;
import java.util.*;

public class BOJ2563 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[][] visit = new boolean[101][101];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int a = 0; a < 10; a++) {
                for (int b = 0; b < 10; b++) {
                    visit[a + x][b + y] = true;
                }
            }
        }

        int num = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                num += visit[i][j] ? 1 : 0;
            }
        }

        bw.write(Integer.toString(num));
        bw.flush();
    }
}