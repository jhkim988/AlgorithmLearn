import java.util.Scanner;

class BOJ2884 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int h = stdIn.nextInt();
        int m = stdIn.nextInt();
        
        int wakeUpH = h;
        int wakeUpM = m - 45;
        if (wakeUpM < 0){
            wakeUpM += 60;
            wakeUpH--;
        }
            System.out.print(wakeUpH + " " + wakeUpM);
    }
}