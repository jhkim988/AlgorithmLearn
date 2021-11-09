import java.io.*;
import java.util.*;

public class BOJ16917 {
  static int INF = Integer.MAX_VALUE / 2;
  static int[] price = new int[3];
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    for (int i = 0; i < 3; i++) {
      price[i] = Integer.parseInt(st.nextToken());
    }
    int needSauce = Integer.parseInt(st.nextToken());
    int needFried = Integer.parseInt(st.nextToken());

    int minVal = bruteForce(needSauce, needFried);
    bw.write(minVal + "\n");
    bw.flush();
  }  
  static int bruteForce(int needSauce, int needFried) {
    int val1 = INF;
    int val2 = INF;
    int val3 = INF;
    // System.out.println(needSauce + ", " + needFried);
    if (needSauce == 0) {
      return needFried * price[1];
    }
    if (needFried == 0) {
      return needSauce * price[0];
    }
    if (needSauce > 0) {
      val1 = bruteForce(0, needFried) + needSauce * price[0];
    }
    if (needFried > 0) {
      val2 = bruteForce(needSauce, 0) + needFried * price[1];
    }
    if (needSauce > 0 || needFried > 0) {
      if (needSauce > needFried) {
        val3 = bruteForce(needSauce - needFried, 0) + 2 * needFried * price[2];
        val3 = Math.min(val3, 2 * needSauce * price[2]);
      } else {
        val3 = bruteForce(0, needFried - needSauce) + 2 * needSauce * price[2];
        val3 = Math.min(val3, 2 * needFried * price[2]);
      }
    }
    return Math.min(val1, Math.min(val2, val3));
  }
}
