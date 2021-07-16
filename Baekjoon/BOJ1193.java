import java.util.Scanner;

public class BOJ1193 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int X = scn.nextInt();
        scn.close();

        // 1. find N such that (N*(N+1)/2) <= X <= ((N+1)(N+2)/2)
        int N = 1;
        while (N * (N + 1) / 2 < X) {
            N++;
        }
        int prevNum = N * (N - 1) / 2;

        // 2. find X with m'th diagonal element
        int diagNum = 1;
        while ((diagNum++) + prevNum != X);
        diagNum--;

        if (N % 2 == 0) {
            // up-right -> down-left, start 1/N
            System.out.print(diagNum + "/" +  (N - diagNum + 1));
        } else {
            // down-left -> up-right, start N/1
            System.out.print((N - diagNum + 1) + "/" + diagNum);
        }
    }
}
