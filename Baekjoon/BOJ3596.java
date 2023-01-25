import java.io.*;
import java.util.*;

public class BOJ3596 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        boolean[] check = new boolean[n];
        int[] grundy = new int[n+1];
        for (int i = 1; i <= 3; i++) grundy[i] = 1;

        for (int i = 4; i <= n; i++) {
            Arrays.fill(check, false);
            for (int j = 0; j+3 <= i; j++) {
                int xor = 0;
                if (j >= 2) xor ^= grundy[j-2];
                xor ^= grundy[i-j-3];
                check[xor] = true;
            }

            for (int j = 0; j <= i; j++) {
                if (!check[j]) {
                    grundy[i] = j;
                    break;
                }
            }
        }
        bw.write(grundy[n] != 0 ? "1" : "2");
        bw.flush();
    }
}
