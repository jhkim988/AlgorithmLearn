import java.io.*;
import java.util.*;

public class BOJ16925 {
    private static class Pair {
        String str;
        int idx;

        Pair(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Pair[] inputs = new Pair[2 * n - 2];
        for (int i = 0; i < 2 * n - 2; i++) {
            String str = br.readLine();
            inputs[i] = new Pair(str, i);
        }

        Arrays.sort(inputs, (a, b) -> Integer.compare(a.str.length(), b.str.length()));
        
        List<String> candidates = new ArrayList<>();
        candidates.add(inputs[inputs.length-2].str.charAt(0) + inputs[inputs.length-1].str);
        candidates.add(inputs[inputs.length-1].str.charAt(0) + inputs[inputs.length-2].str);

        String answer = null;
        char[] result = new char[2*n-2];
        for (String origin : candidates) {
            answer = origin;
            boolean fail = false;
            for (int i = 0; i < inputs.length; i+=2) {
                if (origin.startsWith(inputs[i].str) && origin.endsWith(inputs[i+1].str)) {
                    result[inputs[i].idx] = 'P';
                    result[inputs[i+1].idx] = 'S';
                } else if (origin.startsWith(inputs[i+1].str) && origin.endsWith(inputs[i].str)) {
                    result[inputs[i].idx] = 'S';
                    result[inputs[i+1].idx] = 'P';
                } else {
                    fail = true;
                    break;
                }
            }
            if (!fail) {
                break;
            }
        }

        bw.write(answer);
        bw.newLine();
        for (char ch : result) {
            bw.write((char) ch);
        }
        bw.flush();
    }
}
