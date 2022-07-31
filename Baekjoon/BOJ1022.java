import java.io.*;
import java.util.*;

public class BOJ1022 {
  static int[] boundary;
  static int[] rowDi = {0, -1, 0, 1}, colDi = {1, 0, -1, 0};
  private static class Pair {
    int row, col, id;
    Pair(int row, int col, int id) {
      this.row = row;
      this.col = col;
      this.id = id;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    boundary = new int[4];
    for (int i = 0; i < 4; i++) {
      boundary[i] = Integer.parseInt(st.nextToken());
    }  
    int[][] ret = new int[boundary[2]-boundary[0]+1][boundary[3]-boundary[1]+1];
    int totNum = ret.length*ret[0].length;

    int lenStat = 2;
    int direction = 0;
    Pair p = new Pair(0, 0, 1);
    int num = record(ret, 0, 0, 1) ? 1 : 0;
    if (num >= totNum) print(ret, 1);
    while (num < totNum) {
      for (int i = 1; i <= lenStat/2; i++) {
        if (record(ret, p.row+rowDi[direction]*i, p.col+colDi[direction]*i, ++p.id)) num++;
        if (num >= totNum) {
          print(ret, p.id);
          return;
        }
      }
      p.row += rowDi[direction]*lenStat/2;
      p.col += colDi[direction]*lenStat/2;
      direction = (direction+1)%4;
      lenStat++;
    }
  }

  static boolean record(int[][] ret, int row, int col, int id) {
    if (boundary[0] > row || boundary[2] < row || boundary[1] > col || boundary[3] < col) return false;
    ret[row-boundary[0]][col-boundary[1]] = id;
    return true;
  }
  static void print(int[][] ret, int max) throws IOException {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = digit(max);
    for (int i = 0; i < ret.length; i++) {
      for (int j = 0; j < ret[0].length; j++) {
        int digit = digit(ret[i][j]);
        for (int k = 0; k < len-digit; k++) {
          bw.write(' ');
        }
        bw.write(Integer.toString(ret[i][j]));
        bw.write(' ');
      }
      bw.newLine();
    }
    bw.flush();
  }
  static int digit(int x) {
    int len = 0;
    while (x > 0) {
      len++;
      x /= 10;
    }
    return len;
  }
}