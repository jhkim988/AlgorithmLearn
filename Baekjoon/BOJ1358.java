import java.io.*;
import java.util.*;

public class BOJ1358 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int w = Integer.parseInt(st.nextToken());
    int h = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());
    int p = Integer.parseInt(st.nextToken());
    

    int num = 0;
    while (p-- > 0) {
      st = new StringTokenizer(br.readLine());
      int testx = Integer.parseInt(st.nextToken());
      int testy = Integer.parseInt(st.nextToken());
      if (inRect(x, y, w, h, testx, testy) || inCircle(x, y+h/2, h/2, testx, testy) || inCircle(x+w, y+h/2, h/2, testx, testy)) num++;
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
  static boolean inRect(int x, int y, int w, int h, int testx, int testy) {
    return x <= testx && testx <= x+w && y <= testy && testy <= y+h;
  }
  static boolean inCircle(int cx, int cy, int r, int testx, int testy) {
    return (cx-testx)*(cx-testx)+(cy-testy)*(cy-testy) <= r*r;
  }
}
