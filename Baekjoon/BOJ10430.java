import java.util.Scanner;
class BOJ10430 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int A = stdIn.nextInt();
        int B = stdIn.nextInt();
        int C = stdIn.nextInt();

        System.out.println((A+B)%C);
        System.out.println(((A%C) + (B%C))%C);
        System.out.println((A*B)%C);
        System.out.println(((A%C) * (B%C))%C);
        stdIn.close();
    }
}