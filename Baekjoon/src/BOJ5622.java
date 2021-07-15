import java.util.Scanner;

public class BOJ5622 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        char[] input = scn.next().toCharArray();
        scn.close();

        int totalTime = 0;
        for (char c : input) {
            totalTime += calculateTime(alphabetToInt(c));
        }

        System.out.print(totalTime);
    }

    static int alphabetToInt(char c) {
        if (c == 'Z') {
            return ((int)c - 2 - 65) / 3 + 1;
        }
        if (c >= 'S') {
            return ((int)c - 1 - 65) / 3 + 1;
        }
        return (((int)c - 65) / 3) + 1;
    }

    static int calculateTime(int num) {
        return num + 2;
    }
}
