import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class BOJ2742 {
    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);
            int N = scn.nextInt();
            scn.close();
    
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osw);
    
            for (int i = N; i > 0; i--) {
                bw.write(i + "\n");
            }
    
            bw.flush();
            bw.close();
            osw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
