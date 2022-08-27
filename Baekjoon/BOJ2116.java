import java.io.*;
import java.util.*;

public class BOJ2116 {
  static int[][] cubes;
  static int[][] side = {
    { 1, 2, 3, 4 },
    { 0, 2, 4, 5 },
    { 0, 1, 3, 5 },
    { 0, 2, 4, 5 },
    { 0, 1, 3, 5 },
    { 1, 2, 3, 4 }
  };
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    cubes = new int[n][6];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 6; j++) {
        cubes[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int[] up = { 5, 3, 4, 1, 2, 0 };

    int max = 0;
    for (int buttomIdx = 0; buttomIdx < 6; buttomIdx++) {
      int sum = maxSide(0, cubes[0][buttomIdx]);
      int upNum = cubes[0][up[buttomIdx]];
      for (int level = 1; level < n; level++) {
        sum += maxSide(level, upNum);
        int idx = 0;
        for (; idx < 6; idx++) {
          if (cubes[level][idx] == upNum) break;
        }
        upNum = cubes[level][up[idx]];
      }
      if (max < sum) max = sum;
    }
    bw.write(Integer.toString(max));
    bw.flush();
  }
  static int maxSide(int level, int buttom) {
    int buttomIdx = 0;
    for (; buttomIdx < 6; buttomIdx++) {
      if (cubes[level][buttomIdx] == buttom) break;
    }
    int maxSide = 0;
    for (int i = 0; i < 4; i++) {
      if (maxSide < cubes[level][side[buttomIdx][i]]) maxSide = cubes[level][side[buttomIdx][i]];
    }
    return maxSide;
  }
}
