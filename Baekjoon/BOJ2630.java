import java.io.*;
import java.util.*;

public class BOJ2630 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    int[][] data = new int[N][N];
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    bw.write(makeColorPaper(data, 0) + "\n");
    bw.write(makeColorPaper(data, 1) + "\n");
    bw.flush();
  } 
  static int makeColorPaper(int[][] data, int color) {
    int N = data.length;
    return  makeColorPaper(data, 0, N, 0, N, color);
  }
  static int makeColorPaper(int[][] data, int widStart, int widEnd, int heiStart, int heiEnd, int color) {
    // check, white = 0, blue = 1
    if (widEnd - widStart <= 1 && heiEnd - heiStart <= 1) {
      return data[widStart][heiStart] == color ? 1 : 0;
    }
    boolean flag = true;
    for (int i = widStart; i < widEnd; i++) {
      for (int j = heiStart; j < heiEnd; j++) {
        if (data[i][j] != color) {
          flag = false;
          break;
        }
      }
    }
    int sum = 0;
    if (!flag) {
      sum += makeColorPaper(data, widStart, (widStart + widEnd) / 2, heiStart, (heiStart + heiEnd) / 2, color);
      sum += makeColorPaper(data, widStart, (widStart + widEnd) / 2, (heiStart + heiEnd) / 2, heiEnd, color);
      sum += makeColorPaper(data, (widStart + widEnd) / 2, widEnd, heiStart, (heiStart + heiEnd) / 2, color);
      sum += makeColorPaper(data, (widStart + widEnd) / 2, widEnd, (heiStart + heiEnd) / 2, heiEnd, color);
      return sum;
    }
    return 1;
  }
}
