import java.io.*;
import java.lang.Integer;

public class BOJ15552 {
        public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osw);
    
            int T = Integer.parseInt(br.readLine()); // readLine return String type
            
            String[] str;
            int sum;
            for(int i = 0; i < T; i++) {
                str = br.readLine().split(" ");
                sum = Integer.parseInt(str[0]) + Integer.parseInt(str[1]);
                bw.write(sum + "\n");
            }
            bw.flush();

            br.close();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
