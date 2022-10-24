import java.io.*;
import java.util.*;

public class BOJ19539 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num2 = 0;
        int num1 = 0;
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            num1 += input % 2;
            num2 += input / 2;
        }
        if (num2 >= num1 && (num2 - num1) % 3 == 0) bw.write("YES");
        else bw.write("NO");
        bw.flush();
    }
}