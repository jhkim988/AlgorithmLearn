import java.io.*;
import java.util.*;

public class BOJ10158 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int w = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(br.readLine());
    if ((x+t)/w % 2 == 0) {
      x = (x+t)%w;
    } else {
      x = w-(x+t)%w;
    }
    if ((y+t)/h % 2 == 0) {
      y = (y+t)%h;
    } else {
      y = h-(y+t)%h;
    }
    bw.write(Integer.toString(x));
    bw.write(' ');
    bw.write(Integer.toString(y));
    bw.flush();
  }
}
