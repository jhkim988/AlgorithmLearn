import java.util.Scanner;

public class BOJ1712 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        long A = scn.nextLong();
        long B = scn.nextLong();
        long C = scn.nextLong();
        scn.close();
    
        if (C <= B) {
            System.out.print(-1);
            return;
        }

        long num = A / (C - B);
        while (((C - B) * num) <= A) {
            num++;
        }
    
        System.out.print(num);
    }
}