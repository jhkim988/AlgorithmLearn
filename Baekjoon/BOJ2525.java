import java.io.*;
import java.util.*;

public class BOJ2525 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int h = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int add = Integer.parseInt(br.readLine());
    m += add;
    h += m/60;
    m %= 60;
    h %= 24;
    bw.write(Integer.toString(h));
    bw.write(' ');
    bw.write(Integer.toString(m));
    bw.newLine();
    bw.flush();
  }  
}
