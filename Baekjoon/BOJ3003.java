import java.io.*;
import java.util.*;

public class BOJ3003 {
    public static void main(String[] args) throws IOException {
        int[] std = {1, 1, 2, 2, 2, 8};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            int input = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(std[i]-input));
            bw.write(' ');            
        }
        bw.flush();
    }
}
