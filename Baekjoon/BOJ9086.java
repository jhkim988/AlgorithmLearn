import java.io.*;

public class BOJ9086 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numTest = Integer.parseInt(br.readLine());
        while (numTest-- > 0) {
            String input = br.readLine();
            bw.write(input.charAt(0));
            bw.write(input.charAt(input.length()-1));
            bw.newLine();
        }
        bw.flush();
    }
}
