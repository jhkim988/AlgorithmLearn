import java.util.Scanner;

public class BOJ1546 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();

        int sum = 0;
        int max = 0;
        int score;
        for (int i = 0; i < N; i++) {
            score = scn.nextInt();
            sum += score;
            if (max < score) {
                max = score;
            }
        }
        scn.close();
        
        double result = ((double) sum) * 100 / (max * N);
        System.out.print(result);
    }
}
