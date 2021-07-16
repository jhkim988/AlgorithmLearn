import java.util.Scanner;

public class BOJ2577 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        long A, B, C;
        A = scn.nextLong();
        B = scn.nextLong();
        C = scn.nextLong();
        scn.close();
        
        long product = A * B * C;
        
        short[] arr = new short[10];
        while(product != 0) {
            arr[(int)(product % 10)]++;
            product /= 10;
        }

        for(int i = 0; i < 9; i++) {
            System.out.println(arr[i]);
        }
        System.out.println(arr[9]);
    }
}
