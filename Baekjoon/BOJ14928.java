import java.io.*;

public class BOJ14928 {
	public static void main (String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    long temp = 0;
    for (int i = 0; i < input.length(); i++) {
      temp = (temp * 10 + (input.charAt(i) - '0')) % 20000303;
    }
    bw.write(temp + "\n");
    bw.flush();
  }
}