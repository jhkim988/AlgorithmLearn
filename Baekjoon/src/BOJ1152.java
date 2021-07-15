import java.io.*;

public class BOJ1152 {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            
            String[] input = br.readLine().split(" ");

            int minus = 0;
            if (input.length > 0 && input[0].equals("")) {
                minus--;
            }
            System.out.print(input.length + minus);
    
            br.close();
            isr.close();
        } catch (IOException e) {

        }
    }
}
