import java.io.*;
import java.util.*;

public class BOJ3034 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());
    int test = w*w+h*h;
    while (n-- > 0) {
      int input = Integer.parseInt(br.readLine());
      if (input*input <= test) bw.write("DA\n");
      else bw.write("NE\n");
    }
    bw.flush();
  }
}
