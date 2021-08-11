import java.util.Scanner;
class BOJ10869 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int A = stdIn.nextInt();
        int B = stdIn.nextInt();

        System.out.println(A + B);
        System.out.println(A - B);
        System.out.println(A * B);
        System.out.println(A / B);
        System.out.println(A % B);
        stdIn.close();
    }
}