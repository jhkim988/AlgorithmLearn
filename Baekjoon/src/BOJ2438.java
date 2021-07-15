import java.io.*;
import java.util.Scanner;

public class BOJ2438 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        scn.close();

        try {
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osw);
    
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < i; j++) {
                    bw.write("*");
                }
                bw.newLine();
            }
    
            bw.flush();
            bw.close();
            osw.close();
        
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
