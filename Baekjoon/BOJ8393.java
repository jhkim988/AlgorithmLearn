import java.util.Scanner;

class BOJ8393 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int num = stdIn.nextInt();
        stdIn.close();
        System.out.print(num*(num+1)/2);
    }
}