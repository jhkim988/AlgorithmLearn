import java.util.Scanner;

public class BOJ1904 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        int size = N == 1 ? 2 : N;
        scn.close();

        int[] memo = new int[size]; // memo[i] : solve(i + 1)
        memo[0] = 1;
        memo[1] = 2;
        System.out.println(solve(N - 1, memo));
    }
    static int solve(int N, int[] memo) {
        if (memo[N] != 0) {
            return memo[N];
        } else {
            memo[N] = (solve(N - 1, memo) + solve(N - 2, memo)) % 15746;
            return memo[N];
        }
    }
}
