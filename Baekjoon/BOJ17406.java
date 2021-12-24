import java.io.*;
import java.util.*;

public class BOJ17406 {
  private static class Pair {
    int row, col, len;
    Pair(int row, int col, int len) {
      this.row = row;
      this.col = col;
      this.len = len;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    int numOper = Integer.parseInt(st.nextToken());
    int[][] arr = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    ArrayList<Pair> opers = new ArrayList<>(numOper);
    for (int i = 0; i < numOper; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      opers.add(new Pair(r - 1, c - 1, s));
    }
    boolean[] check = new boolean[numOper];
    bw.write(recur(0, arr, check, opers) + "\n");
    bw.flush();
  }
  static int recur(int depth, int[][] arr, boolean[] check, ArrayList<Pair> opers) {
    if (depth >= opers.size()) {
      return calc(arr);
    }
    int val = Integer.MAX_VALUE;
    for (int i = 0; i < opers.size(); i++) {
      if (check[i]) continue;
      check[i] = true;
      rot(arr, opers.get(i), false);
      val = Math.min(val, recur(depth + 1, arr, check, opers));
      check[i] = false;
      rot(arr, opers.get(i), true);
    }
    return val;
  }
  static int calc(int[][] arr) {
    int val = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      for (int j = 0; j < arr[0].length; j++) {
        sum += arr[i][j];
      }
      if (sum < val) val = sum;
    }
    return val;
  }
  static void rot(int[][] arr, Pair oper, boolean ccw) {
    if (ccw) {
      for (int orbit = oper.len; orbit > 0; orbit--) {
        int tmp = arr[oper.row + orbit][oper.col + orbit];
        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row + orbit][oper.col + orbit - idx] = arr[oper.row + orbit][oper.col + orbit - idx - 1];
        }
        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row + orbit - idx][oper.col - orbit] = arr[oper.row + orbit - idx - 1][oper.col - orbit];
        }
        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row - orbit][oper.col - orbit + idx] = arr[oper.row - orbit][oper.col - orbit + idx + 1];
        }

        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row - orbit + idx][oper.col + orbit] = arr[oper.row - orbit + idx + 1][oper.col + orbit]; 
        }
        arr[oper.row + orbit - 1][oper.col + orbit] = tmp;
      }
    } else {
      for (int orbit = oper.len; orbit > 0; orbit--) {
        int tmp = arr[oper.row - orbit][oper.col - orbit];
        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row - orbit + idx][oper.col - orbit] = arr[oper.row - orbit + idx + 1][oper.col - orbit]; 
        }
        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row + orbit][oper.col - orbit + idx] = arr[oper.row + orbit][oper.col - orbit + idx + 1];
        }
        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row + orbit - idx][oper.col + orbit] = arr[oper.row + orbit - idx - 1][oper.col + orbit];
        }
        for (int idx = 0; idx < 2 * orbit; idx++) {
          arr[oper.row - orbit][oper.col + orbit - idx] = arr[oper.row - orbit][oper.col + orbit - idx - 1];
        }
        arr[oper.row - orbit][oper.col - orbit + 1] = tmp;
      }
    }
  }
}