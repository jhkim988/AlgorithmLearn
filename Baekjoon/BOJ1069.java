import java.io.*;
import java.util.*;

public class BOJ1069 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    double x = Double.parseDouble(st.nextToken());
    double y = Double.parseDouble(st.nextToken());
    int D = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());

    double dist = dist(x, y);
    double cost = dist;

    int count = (int) dist / D;
    dist -= count * D;

    if (count == 0) {
      cost = Math.min(cost, (double) T + ((double) D - dist));
      cost = Math.min(cost, (double) T * 2);
    } else {
      cost = Math.min(cost, (double) count * T + dist);
      cost = Math.min(cost, (double) (count + 1) * T); 
    }

    bw.write(cost + "\n");
    bw.flush();
  }
  static double dist(double x, double y) {
    return Math.sqrt(x*x + y*y);
  }
}
