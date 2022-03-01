import java.io.*;

public class BOJ1439 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int zero = 0;
    int one = 0;
    int ptr = 0;
    while (ptr < input.length) {
      char type = input[ptr++];
      if (type == '0') {
        while (ptr < input.length && type == input[ptr]) ptr++;
        zero++;
      } else {
        while (ptr < input.length && type == input[ptr]) ptr++;
        one++;
      }
    }
    bw.write(Integer.toString(Integer.min(zero, one)));
    bw.newLine();
    bw.flush();
  }
}