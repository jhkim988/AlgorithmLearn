import java.io.*;

public class BOJ2744 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    for (int i = 0; i < input.length; i++) {
      if ('a' <= input[i]) input[i] -= 'a'-'A';
      else input[i] += 'a'-'A';
    }
    bw.write(input);
    bw.flush();
  }
}
