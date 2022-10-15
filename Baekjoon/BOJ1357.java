import java.io.*;
import java.util.*;

public class BOJ1357 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();
        bw.write(reverse((Integer.parseInt(reverse(a)) + Integer.parseInt(reverse(b)) + "")));
        bw.flush();
    }
    private static String reverse(String str) {
        boolean zero = true;
        StringBuilder sb = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            if (zero && str.charAt(i) == '0') continue;
            zero = false;
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
