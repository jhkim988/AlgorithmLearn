import java.util.Scanner;

public class BOJ10818 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = scn.nextInt();
        }
        scn.close();

        int min = 1000001;
        int max = -1000001;

        for(int el : arr) {
            if (el < min) {
                min = el;
            }
            if (max < el) {
                max = el;
            }
        }

        System.out.print(min + " " + max);
    }
}
