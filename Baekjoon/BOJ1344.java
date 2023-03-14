import java.io.*;
import java.util.*;

public class BOJ1344 {
    private static double teamA, teamB;
    private static double[] probs = new double[4];
    private static boolean[] isPrime = new boolean[20];
    private static double[][][] dp = new double[18][18][18];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        teamA = Integer.parseInt(br.readLine()); teamA /= 100;
        teamB = Integer.parseInt(br.readLine()); teamB /= 100;
        probs[0] = 1-teamA;
        probs[1] = teamA;
        probs[2] = 1-teamB;
        probs[3] = teamB;
    
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i*i <= isPrime.length; i++) {
            if (!isPrime[i]) continue;
            for (int j = i<<1; j < isPrime.length; j += i) isPrime[j] = false;
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1.0);
            }
        }
        bw.write(Double.toString(recur(0, 0, 0)));
        bw.flush();
    }
    private static double recur(int depth, int scoreA, int scoreB) {
        if (depth >= 18) {
            if (isPrime[scoreA] || isPrime[scoreB]) return 1.0;
            return 0.0;
        }
        if (dp[depth][scoreA][scoreB] >= -1e-9) return dp[depth][scoreA][scoreB];
        double prob = 0.0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                prob += probs[i]*probs[j+2]*recur(depth+1, scoreA+i, scoreB+j);
            }
        }
        return dp[depth][scoreA][scoreB] = prob;
    }
}