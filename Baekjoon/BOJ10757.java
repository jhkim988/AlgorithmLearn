import java.util.Scanner;

public class BOJ10757 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String A = scn.next();
        String B = scn.next();
        scn.close();

        // 1. Set small length string to B.
        if (A.length() < B.length()) {
            String tmp;
            tmp = A;
            A = B;
            B = tmp;
        }
        // System.out.println("A: " + A);
        // System.out.println("B: " + B);

        // 2. sum
        String result = "";
        boolean overDigit = false;
        for (int i = 0; i < B.length(); i++) {
            int aSum = Integer.parseInt(A.charAt(A.length() - i - 1) + "");
            int bSum = Integer.parseInt(B.charAt(B.length() - i - 1) + "");
            // System.out.println("aSum: " + aSum);
            // System.out.println("bSum: " + bSum);
            int tmp = aSum + bSum ;
            if (overDigit) {
                tmp++;
            }
            result = tmp % 10 + result;
            
            if (tmp / 10 > 0) {
                overDigit = true;
            } else {
                overDigit = false;
            }
        }

        for (int i = B.length(); i < A.length(); i++) {
            int aSum = Integer.parseInt(A.charAt(A.length() - i - 1) + "");
            int tmp = aSum;
            // System.out.println("aSum: " + aSum);
            if (overDigit) {
                tmp++;
            }
            result = tmp % 10 + result;
            if (tmp / 10 > 0) {
                overDigit = true;
            } else {
                overDigit = false;
            }
        }

        if (overDigit) {
            result = 1 + result;
        }

        System.out.print(result);
    }
}
