import java.io.*;

public class BOJ2902 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    bw.write(input[0]);
    for (int i = 1; i < input.length; i++) {
      if (input[i] == '-') bw.write(input[i+1]);
    }
    bw.newLine();
    bw.flush();
  }
}