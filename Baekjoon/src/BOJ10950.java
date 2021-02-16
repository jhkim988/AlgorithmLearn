import java.util.Scanner;
class BOJ10950{
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int num = stdIn.nextInt();
        for(int i = 0; i < num; ++i) {
            int a = stdIn.nextInt();
            int b = stdIn.nextInt();
            System.out.println(a + b);
        }
        stdIn.close();
    }
}