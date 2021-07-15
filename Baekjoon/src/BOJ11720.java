import java.util.Scanner;

public class BOJ11720 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        String str = scn.next();

        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += Integer.parseInt(str.charAt(i) + "");
        }

        System.out.println(sum);
    }
}
