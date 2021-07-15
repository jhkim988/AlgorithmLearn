import java.util.Scanner;

public class BOJ1065 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int sol = solution(num);
        System.out.print(sol);
    }
    static int solution(int num) {
        if (num <= 99) {
            return num;
        }
        if (num == 1000) {
            num--;
        }

        int count = 99;
        for(int i = 100; i <= num; i++) {
            if (isHanNumber(i)) {
                count++;
            }
        }

        return count;
    }
    static boolean isHanNumber(int num) {
        if (num <= 99) {
            return true;
        }

        if (num == 1000) {
            return false;
        }

        int num1 = num % 10;
        int num100 = num / 100;
        int num10 = (num - num1)/10 % 10;

        if ((num1 + num100) == (num10 * 2)) {
            return true;
        }
        return false;
    }
}
