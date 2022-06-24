import java.io.*;
import java.util.*;

public class BOJ2530 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    c += Integer.parseInt(br.readLine());
    b += c/60;
    c %= 60;
    a += b/60;
    b %= 60;
    a %= 24;
    bw.write(Integer.toString(a));
    bw.write(' ');
    bw.write(Integer.toString(b));
    bw.write(' ');
    bw.write(Integer.toString(c));
    bw.flush();
  }
}
