import java.io.*;
import java.util.*;

public class BOJ9461 {
    public static void main(String [] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int numTest = Integer.parseInt(br.readLine());
            for (int i = 0 ; i < numTest; i++) {
                bw.write(P(Integer.parseInt(br.readLine())) + "\n");
            }
            bw.flush();
            bw.close();
            br.close();
        } catch (IOException e) {

        }
    }
    static long P(int N) {
        long[] memo = new long[100]; // memo[i]: P(i + 1)
        Queue<Long> summand = new LinkedList<>();

        // initialize
        for (int i = 0; i < 5; i++) {
            if (i < 3) {
                memo[i] = 1;
                summand.add(1L);
            } else {
                memo[i] = 2;
                summand.add(2L);
            }
        }
        
        if (N <= 5) {
            return memo[N - 1];
        } else {
            return P(N, memo, summand);
        }
    }
    static long P(int N, long[] memo, Queue<Long> summand) {
        if (memo[N - 1] != 0) {
            return memo[N - 1];
        } else {
            memo[N - 2] = P(N - 1, memo, summand);
            memo[N - 1] = memo[N - 2] + summand.poll();
            summand.add(memo[N - 1]);
            return memo[N - 1];
        }
    }
}
