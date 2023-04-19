import java.io.*;
import java.util.*;

public class BOJ2870 {
    public static class FastIO {
        private BufferedReader br;
        private BufferedWriter bw;
        public FastIO() {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        public String next() throws IOException {
            return br.readLine();
        }
        public void write(String str) throws IOException {
            bw.write(str);
        }
        public void newLine() throws IOException {
            bw.newLine();
        }
        public void flush() throws IOException {
            bw.flush();
        }
    }
    public static void main(String[] args) throws IOException {
        FastIO io = new FastIO();
        int n = Integer.parseInt(io.next());
        List<String> numbers = new ArrayList<>();
        while (n-- > 0) {
            String[] tokens = io.next().split("[^0-9]");
            for (String token : tokens) {
                String removeLeadingZero = removeLeadingZero(token);
                if (token.length() > 0 && removeLeadingZero.length() == 0) {
                    numbers.add("0");
                }
                if (removeLeadingZero.length() > 0) {
                    numbers.add(removeLeadingZero);
                }
            }
        }
        Collections.sort(numbers, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        });
        for (String number: numbers) {
            io.write(number);
            io.newLine();
        }

        io.flush();
    }

    private static String removeLeadingZero(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }
        return str.substring(i);
    }
}
