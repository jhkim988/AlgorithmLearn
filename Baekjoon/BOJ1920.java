import java.io.*;
import java.util.Arrays;

public class BOJ1920 {
    public static void main(String [] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            // int N = Integer.parseInt(br.readLine());
            Integer.parseInt(br.readLine());
            String[] data1 = br.readLine().split(" ");
            int M = Integer.parseInt(br.readLine());
            String[] data2 = br.readLine().split(" ");

            Arrays.sort(data1);

            for (int i = 0; i < M; i++) {
                if (Arrays.binarySearch(data1, data2[i]) >= 0) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }               
            }
            bw.flush();
        } catch (IOException e) {
            // do nothing
        }
    }
}
