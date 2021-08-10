import java.util.Scanner;

class BOJ14681 {
    public static void main (String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int x = stdIn.nextInt();
        int y = stdIn.nextInt();
        stdIn.close();
        if (x > 0){
            if (y > 0)
                System.out.print(1);
            else
                System.out.print(4);
        } else {
            if (y > 0)
                System.out.print(2);
            else
                System.out.print(3);
        }
    }
}