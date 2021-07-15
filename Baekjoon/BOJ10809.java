import java.util.Scanner;

public class BOJ10809 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        char[] arr = scn.next().toCharArray();
        scn.close();

        int idx = 0;
        int[] result = new int['z' - 'a' + 1];
        for (int i = 'a'; i <= 'z'; i++) {
            result[idx] = -1;
            for (int j = 0; j < arr.length; j++) {
                if (i == arr[j]) {
                    result[idx] = j;
                    break;
                }
            }
            idx++;
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
