import java.io.*;
import java.util.*;

public class BOJ2953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] score = new int[5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                if (i == j) continue;
                score[i] += Integer.parseInt(st.nextToken());
            }
        }

        int idx = 0;
        int max = score[0];
        for (int i = 1; i < 5; i++) {
            if (max < score[i]) {
                idx = i;
                max = score[i];
            }
        }

        bw.write(Integer.toString(idx+1));
        bw.write(' ');
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
