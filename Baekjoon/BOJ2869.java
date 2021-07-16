import java.io.*;

public class BOJ2869 {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String[] line = br.readLine().split(" ");
    
            long A = Long.parseLong(line[0]);
            long B = Long.parseLong(line[1]);
            long V = Long.parseLong(line[2]);

            // find day: A + (day - 1) * (A - B) >= V
            // day >= (V - A) / (A - B) + 1
    
            System.out.print((long) Math.ceil(((double) Math.max((V - A), 0)) / (A - B)) + 1);
        } catch (IOException e) {
            System.out.print("Error");
        }
    }
}
