import java.io.*;
import java.util.*;

public class BOJ10709 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nRow = Integer.parseInt(st.nextToken());
        int nCol = Integer.parseInt(st.nextToken());
        char[][] cloud = new char[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            cloud[i] = br.readLine().toCharArray();
        }

        int[][] foresee = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            int count = -1;
            for (int j = 0; j < nCol; j++) {
                if (cloud[i][j] == 'c') {
                    count = 0;
                } else {
                    if (count != -1) count++;
                }
                foresee[i][j] = count;
            }
        }

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                bw.write(Integer.toString(foresee[i][j]));
                bw.write(' ');
            }
            bw.newLine();
        }
        bw.flush();
    }
}