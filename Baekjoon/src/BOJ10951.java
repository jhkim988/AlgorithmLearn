import java.io.*;

public class BOJ10951 {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
    
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String readline;
            String[] line;
            int num1, num2, sum;
            while ((readline = br.readLine()) != null) {
                line = readline.split(" ");
                num1 = Integer.parseInt(line[0]);
                num2 = Integer.parseInt(line[1]);
                sum = num1 + num2;
                bw.write(sum + "\n");
            }

            bw.flush();
            bw.close();
            osw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
