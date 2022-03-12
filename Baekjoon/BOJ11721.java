import java.io.*;

public class BOJ11721 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int ptr = 0;
    while (ptr < input.length) {
      bw.write(input[ptr++]);
      if (ptr % 10 == 0) bw.newLine();
    }
    bw.flush();
  }
}