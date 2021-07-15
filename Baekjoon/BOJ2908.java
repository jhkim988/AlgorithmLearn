import java.util.Scanner;

public class BOJ2908 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int first = scn.nextInt();
        int second = scn.nextInt();
        scn.close();

        System.out.print(SangeonLook(first) > SangeonLook(second) ? SangeonLook(first) : SangeonLook(second));
    }

    static int SangeonLook(int num) {
        int num1 = num%10;
        int num100 = num/100;

        return num - num1 - num100*100 + num100 + num1*100;
    }
}
