import java.util.Scanner;

class BOJ2753 {
    public static void main(String[] args) {
        Scanner stdIn= new Scanner(System.in);
        int year = stdIn.nextInt();
        
        if ((year%4 == 0 && year%100 != 0) || year % 400 == 0)
            System.out.print(1);
        else
            System.out.print(0);
    }
}