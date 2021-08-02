import java.io.*;
import java.util.*;

public class BOJ7576 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int width;
  static int height;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    width = Integer.parseInt(st.nextToken());
    height = Integer.parseInt(st.nextToken());
    int[][] box = new int[height][width];

    for (int i = 0; i < height; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < width; j++) {
        box[i][j] = Integer.parseInt(st.nextToken());
      }
    }


  }
}
