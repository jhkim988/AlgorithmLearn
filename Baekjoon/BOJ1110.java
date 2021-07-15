import java.util.Scanner;

public class BOJ1110 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        scn.close();

        int tmp = N;
        int counter = 0;

        do {
            if (tmp < 10) {
                tmp = 11 * tmp;
            } else {
               int a = tmp / 10;
               int b = tmp % 10;
               tmp = b * 10 + (a + b) % 10;
            }
            counter++;
        } while (tmp != N);
        System.out.println(counter);
    }
}
