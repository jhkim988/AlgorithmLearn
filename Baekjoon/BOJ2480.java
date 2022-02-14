import java.io.*;
import java.util.*;

public class BOJ2480 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int answer = 0;
    if (a == b && b == c) {
      answer = 10000 + a * 1000;
    } else if (a == b) {
      answer = 1000 + a * 100;
    } else if (a == c) {
      answer = 1000 + a * 100;
    } else if (b == c) {
      answer = 1000 + b * 100;
    } else {
      int max = Integer.max(Integer.max(a, b), c);
      answer = max * 100;
    }
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
}
