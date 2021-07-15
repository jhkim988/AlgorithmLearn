import java.util.Scanner;

class BOJ2588 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int a = stdIn.nextInt();
        int b = stdIn.nextInt();
        
        System.out.println(a * (b%10));
        System.out.println(a * ((b/10)%10));
        System.out.println(a * (b/100));
        System.out.println(a * b);
    }
}