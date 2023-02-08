import java.io.*;
import java.util.*;

public class BOJ14582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] j = new int[9];
        int[] g = new int[9];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 9; i++) {
            j[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 9; i++) {
            g[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;
        int scoreJ = 0, scoreG = 0;
        for (int i = 0; i < 9; i++) {
            scoreJ += j[i];
            if (scoreJ > scoreG) {
                flag = true;
            }
            scoreG += g[i];
            if (scoreJ > scoreG) {
                flag = true;
            }
        }

        bw.write(flag ? "Yes" : "No");
        bw.flush();
    }
}