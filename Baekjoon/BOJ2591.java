import java.io.*;
import java.util.*;

public class BOJ2591 {
    private static char[] input;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input = br.readLine().toCharArray();
        dp = new long[input.length];
        Arrays.fill(dp, -1);
        recur(0);
    
        bw.write(Long.toString(dp[0]));
        bw.flush();
    } 
    private static long recur(int depth) {
        if (depth >= input.length) {
            return 1;
        }
        if (dp[depth] != -1) {
            return dp[depth];
        }
        if (input[depth] == '0') {
            return dp[depth] = 0;
        }
        long ret = recur(depth+1);
        if (depth+1 < input.length) {
            int val = (input[depth] - '0') * 10 + input[depth+1] - '0';
            if (val < 35) ret += recur(depth+2);
        }

        return dp[depth] = ret;
    }  
}
