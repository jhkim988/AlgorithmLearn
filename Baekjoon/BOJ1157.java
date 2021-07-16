import java.util.Scanner;

public class BOJ1157 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        char[] input = scn.next().toUpperCase().toCharArray();
        scn.close();
        
        int[] counter = new int['z' - 'a' + 1];
        int idx = 0;

        for (int i = 'A'; i <= 'Z'; i++) {
            for (char c : input) {
                if (i == (int) c) {
                    counter[idx]++;
                }
            }
            idx++;
        }

        int maxIdx = 0;
        boolean dupli = false;

        for (int i = 0; i < counter.length; i++) {
            if (maxIdx != i && counter[maxIdx] == counter[i]) {
                dupli = true;
            }
            if (counter[maxIdx] < counter[i]) {
                maxIdx = i;
                dupli = false;
            }
        }

        System.out.print(dupli ? "?" : (char) (maxIdx + 65));
    }
}
