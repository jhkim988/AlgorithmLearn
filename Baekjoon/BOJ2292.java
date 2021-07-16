import java.util.Scanner;

public class BOJ2292 {
    public static void main(String[] args) {
        Scanner scn =  new Scanner(System.in);
        long N = scn.nextLong();
        scn.close();
        
        // Find ends of Interval: left <= N <= right
        long right = 1;
        long idx = 0;
        while (N > right) {
            right += 6 * (++idx);
        }
        System.out.print(idx + 1);
    }
}
