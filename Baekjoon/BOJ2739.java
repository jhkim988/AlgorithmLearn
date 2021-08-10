import java.util.Scanner;

class BOJ2739 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int num = stdIn.nextInt();
        
        for (int i = 0; i < 9; ++i)
            System.out.println(num + " * " + (i+1) + " = " + num*(i+1));
    }
}