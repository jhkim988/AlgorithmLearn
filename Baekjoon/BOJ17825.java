import java.io.*;
import java.util.*;

public class BOJ17825 {
  static int[] move;
  static int[][] cross = {
    {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
    {10, 13, 16, 19, 25, 30, 35, 40},
    {20, 22, 24, 25, 30, 35, 40},
    {30, 28, 27, 26, 25, 30, 35, 40},
    {0}
  };
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    move = new int[10];
    for (int i = 0; i < 10; i++) move[i] = Integer.parseInt(st.nextToken());
    int[] stat = {0, 0, 0, 0};
    int[] type = {0, 0, 0, 0};
    int answer = recur(0, stat, type, 0);
    bw.write(Integer.toString(answer));
    bw.flush();
  }
  static int recur(int depth, int[] stat, int[] type, int value) {
    if (depth >= 10) return value;
    int max = 0;
    for (int i = 0; i < 4; i++) {
      if (type[i] == 4) continue;
      int prevStat = stat[i];
      int prevType = type[i];
      if (stat[i] + move[depth] >= cross[type[i]].length) {
        type[i] = 4;
        stat[i] = 0;
      } else {
        stat[i] += move[depth];
        if (type[i] == 0) {
          int add = cross[type[i]][stat[i]];
          if (add == 10 || add == 20 || add == 30) {
            stat[i] = 0;
            type[i] = add/10;
          }
        }
      }      
      boolean duplicate = false;
      if (type[i] != 4) {
        for (int j = 0; j < 4; j++) {
          if (i == j) continue;
          if (stat[i]==stat[j] && type[i]==type[j]) {
            duplicate = true;
            continue;
          }
          if ((type[i] > 0 && type[j] > 0 || cross[type[i]][stat[i]] == 40) && cross[type[i]][stat[i]]==cross[type[j]][stat[j]]) {
            if (cross[type[i]][stat[i]]==30 && ((stat[i]>3 && stat[j]==0) || (stat[j]>3 && stat[i]==0))) continue;
            duplicate = true;
          }
        }
      }
      if (!duplicate) {
        int recur = recur(depth+1, stat, type, value + cross[type[i]][stat[i]]);
        if (max < recur) max = recur;
      } 
      stat[i] = prevStat;
      type[i] = prevType;
    }
    return max;
  }
}
