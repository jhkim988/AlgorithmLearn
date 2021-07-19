import java.util.Scanner;

public class BOJ9663 {
    static int count = 0;
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        scn.close();
        recur(N);
        System.out.println(count);
    }
    static void recur(int N) {
        int[] pos = new int[N]; // pos[i] = j : ith row, jth col queen set.
        boolean[] flag_a = new boolean[N];
        boolean[] flag_b = new boolean[2*N-1];
        boolean[] flag_c = new boolean[2*N-1];
        set(0, N, pos, flag_a, flag_b, flag_c);
    }
    static void set(int i, int N, int[] pos, boolean[] flag_a, boolean[] flag_b, boolean[] flag_c) {
        for (int j = 0; j < N; j++) {
            if (flag_a[j] == false && flag_b[i + j] == false && flag_c[i - j + N - 1] == false) {
                pos[i] = j;
                if (i == (N - 1)) {
                    count++;
                } else {
                    flag_c[i - j + N - 1] = flag_b[i + j] = flag_a[j] = true;
                    set(i + 1, N, pos, flag_a, flag_b, flag_c);
                    flag_c[i - j + N - 1] = flag_b[i + j] = flag_a[j] = false;
                }
            }
        }     
    }
}