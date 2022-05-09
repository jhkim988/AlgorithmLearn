import java.io.*;
import java.util.*;

public class BOJ17825 {
  static int[] move;
  static int[] cross10 = {10, 13, 16, 19, 25, 30, 35, 40};
  static int[] cross20 = {20, 22, 24, 25, 30, 35, 40};
  static int[] cross30 = {30, 28, 27, 26, 25, 30, 35, 40};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    move = new int[10];
    for (int i = 0; i < 10; i++) move[i] = Integer.parseInt(st.nextToken());
    int[] stat = {0, 0, 0, 0};
    int[] type = {0, 0, 0, 0};
              
  }
  static int recur(int depth, int[] stat, int[] type, int value) {
    if (depth == 10) return value;
    int max = 0;
    for (int i = 0; i < 4; i++) {
      int prevStat = stat[i];
      int prevType = type[i];
      if (type[i] == 4) continue;
      if (type[i] != 0) stat[i] += move[depth];      
      else {
        stat[i] += 2*move[depth];
        if (stat[i] == 10) {
          stat[i] = 0;
          type[i] = 1;
        } else if (stat[i] == 20) {
          stat[i] = 0;
          type[i] = 2;
        } else if (stat[i] == 30) {
          stat[i] = 0;
          type[i] = 3;
        }
      }
      int recur = recur(depth+1, stat, type, value + stat[i]);
      if (max < recur) max = recur;
      stat[i] = prevStat;
      type[i] = prevType;
    }
    return max;
  }
}
