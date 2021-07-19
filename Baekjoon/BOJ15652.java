import java.io.*;
import java.util.Scanner;

public class BOJ15652 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        int M = scn.nextInt();
        scn.close();

        recur(N, M);
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            // do nothing
        }
    }
    static void recur(int N, int M) {
        int[] cand = new int[N];
        int[] result = new int[M];
        for (int i = 0; i < N; i++) {
            cand[i] = (i + 1);
        }
        recur(cand, 0, M, result);
    }
    static void recur(int[] cand, int cdpt, int ldpt, int[] result) {
        if (cdpt < ldpt) {
            for (int i = 0; i < cand.length; i++) {
                if (cand[i] == 0) {
                    continue;
                }
                int[] ncand = new int[cand.length];
                for (int j = 0; j < ncand.length; j++) {
                    if (cand[i] <= cand[j]) {
                        ncand[j] = cand[j];
                    }
                }
                result[cdpt] = cand[i];
                recur(ncand, cdpt + 1, ldpt, result);
            }
        } else {
            try {
                for (int i = 0; i < result.length; i++) {
                    bw.write(result[i] + " ");
                }
                bw.write("\n");
            } catch (IOException e) {
                // do nothing
            }
        }
    }
}
