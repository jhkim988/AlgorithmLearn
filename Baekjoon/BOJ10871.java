import java.io.*;
import java.util.StringTokenizer;

public class BOJ10871 {
    public static void main(String[] args) {
        // try {
        //     InputStreamReader isr = new InputStreamReader(System.in);
        //     BufferedReader br = new BufferedReader(isr);

        //     String[] firstLine = br.readLine().split(" ");
        //     int N = Integer.parseInt(firstLine[0]);
        //     int X = Integer.parseInt(firstLine[1]);
        
        //     String[] SecondLine = br.readLine().split(" ");
    
        //     br.close();
        //     isr.close();
    
        //     OutputStreamWriter osw = new OutputStreamWriter(System.out);
        //     BufferedWriter bw = new BufferedWriter(osw);
        //     for (String str : SecondLine) {
        //         if (Integer.parseInt(str) < X) {
        //             bw.write(str);
        //             bw.newLine();
        //         }
        //     }
        //     bw.flush();
        //     bw.close();
        //     osw.close();
        
        // } catch (IOException e) {
        //     System.out.println("Error");
        // }

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            String firstLine = br.readLine();

            // Use StringTokenizer.
            StringTokenizer st = new StringTokenizer(firstLine, " ");
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
        
            String SecondLine = br.readLine();
            st = new StringTokenizer(SecondLine, " ");

            br.close();
            isr.close();
    
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osw);
            String str;
            for (int i = 0; i < N - 1; i++) {
                str = st.nextToken();
                if (Integer.parseInt(str) < X) {
                    bw.write(str + " ");
                }
            }
            str = st.nextToken();
            if (Integer.parseInt(str) < X) {
                bw.write(str);
            }
            
            bw.flush();
            bw.close();
            osw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
