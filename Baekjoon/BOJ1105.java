import java.io.*;
import java.util.*;

public class BOJ1105 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] left = st.nextToken().toCharArray();
        char[] right = st.nextToken().toCharArray();
        bw.write(Integer.toString(answer(left, right)));
        bw.flush();
    }

    private static int answer(char[] left, char[] right) {
        if (left.length != right.length) return 0;
        int ret = 0;
        for (int i = 0; i < left.length && left[i] == right[i]; i++) {
            if (left[i] == '8') ret++;
        }
        return ret;
    }
}
