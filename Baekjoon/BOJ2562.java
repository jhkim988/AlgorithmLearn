import java.util.Scanner;

public class BOJ2562 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        scn.close();
        
        int idx = 0;
        int max = 0;

        int input;
        for (int i = 1; i < 10; i++) {
            input = scn.nextInt();
            if (max < input) {
                idx = i;
                max = input;
            }
        }
        System.out.print(max + "\n" + idx);
    }
}