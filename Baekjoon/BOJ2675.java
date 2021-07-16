import java.util.Scanner;

public class BOJ2675 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int numTest = scn.nextInt();

        int numRepeat;
        char[] charInputs;
        String result = "";

        for (int i = 0; i < numTest; i++) {
            numRepeat = scn.nextInt();
            charInputs = scn.next().toCharArray();

            for (char c : charInputs) {
                for (int j = 0; j < numRepeat; j++) {
                    result += c;
                }
            }
            System.out.println(result);
            result = "";
        }
        scn.close();
    }
}
