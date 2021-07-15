import java.util.Scanner;

public class BOJ3052 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        boolean[] flags = new boolean[42];

        for (int i = 0; i < 10; i++) {
            flags[scn.nextInt() % 42] = true;
        }

        int count = 0;
        for(boolean flag : flags) {
            if (flag) {
                count++;
            }
        }

        System.out.print(count);
    }
}
