import java.io.*;
import java.util.*;

public class BOJ9177 {
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        for (int test = 1; test <= numTest; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] a = st.nextToken().toCharArray();
            char[] b = st.nextToken().toCharArray();
            char[] c = st.nextToken().toCharArray();
            dp = new int[a.length+1][b.length+1];
            for (int i = 0; i < a.length; i++) {
                Arrays.fill(dp[i], -1);
            }
            boolean answer = recur(a, b, c, 0, 0, 0);
            bw.write("Data set ");
            bw.write(Integer.toString(test));
            bw.write(": ");
            bw.write(answer ? "yes\n" : "no\n");
        }
        bw.flush();
    }
    private static boolean recur(char[] a, char[] b, char[] c, int ptrA, int ptrB, int ptrC) {
        if (ptrC >= c.length) return true;
        if (ptrA < a.length && ptrB < b.length && dp[ptrA][ptrB] != -1) return dp[ptrA][ptrB] != 0;
        if (ptrA < a.length && a[ptrA] == c[ptrC] && recur(a, b, c, ptrA+1, ptrB, ptrC+1)) {
            dp[ptrA][ptrB] = 1;
            return true;
        } 
        else if (ptrB < b.length && b[ptrB] == c[ptrC] && recur(a, b, c, ptrA, ptrB+1, ptrC+1)) {
            dp[ptrA][ptrB] = 1;
            return true;
        }
        dp[ptrA][ptrB] = 0;
        return false;
    }
}
