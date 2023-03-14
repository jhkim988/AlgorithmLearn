import java.io.*;
import java.util.*;

public class BOJ2306 {
    private static char[] dna;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        dna = br.readLine().toCharArray();
        dp = new int[dna.length][dna.length];
        for (int i = 0; i < dna.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        bw.write(Integer.toString(recur(0, dna.length-1)));
        bw.flush();
    }
    private static int recur(int left, int right) {
        if (right <= left) return 0;
        if (dp[left][right] != -1) return dp[left][right];
        int ret = Integer.max(recur(left+1, right), recur(left, right-1));
        if (dna[left] == 'a' && dna[right] == 't' || dna[left]  == 'g' && dna[right] == 'c') {
            ret = Integer.max(ret, recur(left+1, right-1) + 2);
        }
        for (int i = left+1; i < right; i++) {
            ret = Integer.max(ret, recur(left, i) + recur(i+1, right));
        }

        return dp[left][right] = ret;
    } 
}
