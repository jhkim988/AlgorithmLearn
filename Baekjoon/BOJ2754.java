import java.io.*;

public class BOJ2754 {
	public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    if (input.length == 1 && input[0] == 'F') {
      bw.write("0.0\n");
    } else {
      double v = 'E' - input[0];
      switch (input[1]) {
        case '+': {
          v += 0.3;
          break;
        } case '-': {
          v -= 0.3;
          break;
        }
      }
      bw.write(Double.toString(v));
      bw.newLine();
    }
    bw.flush();
  }
}