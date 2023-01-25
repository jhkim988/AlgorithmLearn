import java.io.*;
import java.util.*;

public class BOJ11694 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = 0;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            xor ^= input;
            if (input > 1) num++;
        }

        bw.write((num != 0 && xor == 0 || num == 0 && xor == 1) ? "cubelover" : "koosaga");
        bw.flush();
    }
}
