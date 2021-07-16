import java.util.Scanner;

public class BOJ2839 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int weight = scn.nextInt();
        scn.close();
        
        boolean impossible = true;
        if (weight % 5 == 0 || weight % 3 == 0) {
            impossible = false;
        }

        int idx = weight / 5;
        while (idx >= 0 && (weight - idx * 5) % 3 != 0) {
            idx--;
        }

        if (idx >= 0) {
            impossible = false;
        }

        int numSugarBag = impossible ? -1 : idx + (weight - idx * 5) / 3;
        System.out.println(numSugarBag);
    }
}
