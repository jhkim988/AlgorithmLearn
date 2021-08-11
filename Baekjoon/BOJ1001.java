import java.util.*;

class Main {
    public static void main(String[] args){
        Scanner stdIn = new Scanner(System.in);
        int a = stdIn.nextInt();
        int b = stdIn.nextInt();
        stdIn.close();
        System.out.print(a - b);
    }
}