import java.io.*;
import java.util.*;

public class BOJ2582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        char[][] input = new char[n][n];
        for (int i = 0; i < n; i++) {
            input[i] = br.readLine().toCharArray();
        }

        int answer = simulatedAnealing(n, input);
        bw.write(Integer.toString(answer));
        bw.flush();
    } 
    public static int simulatedAnealing(int n, char[][] input) {
        int ret = n*n;
        double t = 1, d = 0.9999, lim = 0.005, k = 10;
        while (t > lim) {
            int e1 = scoring(n, input);
            int flipRow = (int) (Math.random() * n);
            flip(n, input, flipRow);
            int e2 = scoring(n, input);
            
            double p = Math.exp((e1-e2)/(k*t));
            if (p < Math.random()) {
                flip(n, input, flipRow);
                ret = Integer.min(ret, e1);
            } else {
                ret = Integer.min(ret, e2);
            }
            t *= d;
        }
        return ret;
    }
    private static void flip(int n, char[][] input, int row) {
        for (int c = 0; c < n; c++) {
            input[row][c] = input[row][c] == 'T' ? 'H' : 'T';
        }
    }
    private static int scoring(int n, char[][] input) {
        int score = 0;
        for (int c = 0; c < n; c++) {
            int num = 0;
            for (int r = 0; r < n; r++) {
                if (input[r][c] == 'T') num++;
            }
            if (n-num < num) num = n-num;
            score += num;
        }
        return score;
    }
}
