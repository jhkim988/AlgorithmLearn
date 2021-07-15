import java.io.*;

public class BOJ10952 {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
    
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String[] line = br.readLine().split(" ");
            int num1 = Integer.parseInt(line[0]);
            int num2 = Integer.parseInt(line[1]);
            int sum;
            while (num1 != 0 || num2 != 0) {
                sum = num1 + num2;
                bw.write(sum + "\n");
    
                line = br.readLine().split(" ");
                num1 = Integer.parseInt(line[0]);
                num2 = Integer.parseInt(line[1]);
            }

            bw.flush();
            bw.close();
            osw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
