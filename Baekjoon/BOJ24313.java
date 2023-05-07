import java.io.*;

public class BOJ24313 {
    private static class BufferedIO {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        public String readLine() throws IOException {
            return br.readLine();
        }

        public String[] tokenizer() throws IOException {
            return readLine().split(" ");
        }

        public void writeLine(String str) throws IOException {
            bw.write(str);
            bw.newLine();
        }

        public void flush() throws IOException {
            bw.flush();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedIO io = new BufferedIO();
        String[] input = io.tokenizer();
        int a1 = Integer.parseInt(input[0]);
        int a2 = Integer.parseInt(input[1]);
        int c = Integer.parseInt(io.readLine());
        int n0 = Integer.parseInt(io.readLine());

        io.writeLine(isSatisfy(a1, a2, c, n0) ? "1" : "0");
        io.flush();
    }
    private static boolean isSatisfy(int a1, int a2, int c, int n0) {
        return (a1 * n0 + a2 <= c * n0) && a1 <= c;
    }
}
