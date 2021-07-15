import java.io.*;

public class BOJ8958 {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osw);
    
            int numTest = Integer.parseInt(br.readLine());
            for(int i = 0; i < numTest; i++) {
                char[] line = br.readLine().toCharArray();
                int score = 0;
                int tmp = 0;
                for(char c : line) {
                    if (c == 'O') {
                        tmp++;
                        score += tmp;
                    } else {
                        tmp = 0;
                    }
                }
                bw.write(score + "\n");
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
