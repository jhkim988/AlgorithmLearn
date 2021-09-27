import java.io.*;
import java.util.*;

public class BOJ2745 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    char[] input = st.nextToken().toCharArray();
    int B = Integer.parseInt(st.nextToken());
    int len = input.length;
    int result = 0;
    int base = 1;
    for (int ptr = len - 1; ptr >= 0; ptr--) {
      char ch = input[ptr];
      if ('0' <= ch && ch <= '9') {
        result += (ch - '0') * base;
      } else {
        result += (ch - 'A' + 10) * base;
      }
      base *= B;
    }

    bw.write(result + "\n");
    bw.flush();
  }
}
