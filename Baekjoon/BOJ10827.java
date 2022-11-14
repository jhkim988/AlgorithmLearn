import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class BOJ10827 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();        
        int n = Integer.parseInt(st.nextToken());

        int idx = str.indexOf(".");
        int exp = idx == -1 ? 0 : str.length()-idx-1;
    
        double d = Double.parseDouble(str);
        String answer = BigDecimal.valueOf(d).multiply(BigDecimal.valueOf(10).pow(exp)).pow(n).stripTrailingZeros().toString();
        StringBuilder sb = new StringBuilder(answer);
        if (idx != -1) {
            if (exp*n < sb.length()) {
                sb.insert(sb.length() - exp*n, '.');
            } else {
                sb.insert(0, zeros(exp*n-sb.length()+1));
                sb.insert(1, '.');
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
    private static String zeros(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('0');
        }
        return sb.toString();
    }
}