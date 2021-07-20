import java.util.Scanner;

public class BOJ11050 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        int K = scn.nextInt();
        scn.close();
        System.out.println(comb(N, K));
    }
    static int comb(int N, int K) {
        if (K == 0) {
            return 1;
        }
        int tmp = 1;
        for (int i = 0; i < K; i++) {
            tmp *= (N - i);
        }
        for (int i = 0 ; i < K; i++) {
            tmp /= (K - i);
        }
        return tmp;
    }
}
