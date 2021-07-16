import java.util.Scanner;

public class BOJ2475 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] input = new int[5];
        for (int i = 0; i < 5; i++) {
            input[i] = scn.nextInt();
        }
        scn.close();
        
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += input[i] * input[i];
        }
        System.out.print(sum%10);
    }
}
