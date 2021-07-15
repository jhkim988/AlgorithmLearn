import java.io.*;

public class BOJ11021 {
    public static void main(String[] args) {
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
    
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osw);
    
            String[] str;
            int sum;
            int T = Integer.parseInt(br.readLine());
            
            for (int i = 1; i <= T; i++) {
                str = br.readLine().split(" ");
                sum = Integer.parseInt(str[0]) + Integer.parseInt(str[1]);
                bw.write("Case #" + i + ": " + sum + "\n");
            }
            bw.flush();
            bw.close();
            osw.close();
            br.close();
            isr.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
