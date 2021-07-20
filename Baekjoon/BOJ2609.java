import java.util.Scanner;

public class BOJ2609 {
    public static void main(String [] args) {
        Scanner scn = new Scanner(System.in);
        int num1 = scn.nextInt();
        int num2 = scn.nextInt();
        scn.close();
        if (num1 < num2) {
            int tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        System.out.println(gcd(num1, num2) + "\n" + lcm(num1, num2));
    }
    static int lcm (int num1, int num2) {
        int tmp = num1;
        while(tmp % num2 != 0) {
            tmp += num1;
        }
        return tmp;
    }
    static int gcd (int num1, int num2) {
        if (num1 % num2 == 0) {
            return num2;
        }
        return gcd (num2, num1 % num2);
    }
}