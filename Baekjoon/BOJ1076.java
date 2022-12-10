import java.io.*;
import java.util.*;

public class BOJ1076 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Map<String, Long> value = new HashMap<>();
        
        value.put("black", 0L); 
        value.put("brown", 1L);
        value.put("red", 2L);
        value.put("orange", 3L);
        value.put("yellow", 4L);
        value.put("green", 5L);
        value.put("blue", 6L);
        value.put("violet", 7L);
        value.put("grey", 8L);
        value.put("white", 9L);

        long[] inputs = new long[3];
        for (int i = 0; i < 3; i++) {
            inputs[i] = value.get(br.readLine());
        }

        long answer = (inputs[0]*10+inputs[1])*pow(10L, inputs[2]);
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private static long pow(long base, long exp) {
        long ret = 1;
        long pow = base;
        while (exp > 0) {
            if ((exp & 1) == 1) ret *= pow;
            pow *= pow;
            exp >>= 1;
        }
        return ret;
    }
}
