import java.io.*;
import java.util.Arrays;

public class BOJ13034 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[] check = new boolean[n*2];
        int[] grundy = new int[n+1];
        grundy[2] = grundy[3] = 1;
        for (int i = 3; i <= n; i++) {
            Arrays.fill(check, false);
            for (int j = 0; j < i/2; j++) {
                check[grundy[j]^grundy[i-2-j]] = true;
            }
            for (int j = 0; j <= n; j++) {
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
