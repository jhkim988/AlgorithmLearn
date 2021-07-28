import java.io.*;
import java.util.*;

public class BOJ1780 {
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
    for (int i = -1; i <= 1; i++) {
      bw.write(comp(data, i) + "\n");
    }
    bw.flush();
  } 
  static int comp(int[][] data, int code) {
    return comp(data, code, 0, data.length, 0, data.length);
  }
  static int comp(int[][] data, int code, int widStart, int widEnd, int heiStart, int heiEnd) {
    if (widEnd - widStart <= 1 && heiEnd - heiStart <= 1) {
      return data[widStart][heiStart] == code ? 1 : 0;
    }
    boolean flag = true;
    for (int i = widStart; i < widEnd; i++) {
      for (int j = heiStart; j < heiEnd; j++) {
        if (data[i][j] != code) {
          flag = false;
        }
      }
    }
    int sum = 0;
    int interval = (widEnd - widStart) / 3;
    if (!flag) {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          sum += comp(data, code, widStart + i * interval, widStart + (i + 1) * interval, heiStart + j * interval, heiStart + (j + 1) * interval);
        }
      }
    } else {
      sum = 1;
    }
    return sum;
  }
}
