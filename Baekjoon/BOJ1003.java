import java.io.*;

public class BOJ1003 {
    static int ptr = 2;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int numTest = Integer.parseInt(br.readLine());
            int[] hist = new int[42];
            hist[0] = 1;
            hist[1] = 0;
    
            for (int i = 0; i < numTest; i++) {
                int input = Integer.parseInt(br.readLine());
                makeHist(hist, input + 1);
                bw.write(hist[input] + " " + hist[input + 1] + "\n");
            }
            bw.flush();
        } catch (IOException e) {

        }
    }
    static void makeHist(int[] hist, int input) {
        if (input < 2) {
            return;
        } 
        while(ptr <= input) {
            // System.out.println("Call");
            hist[ptr] = hist[ptr - 1] + hist[ptr - 2];
            ptr++;
        }        
    }
}
