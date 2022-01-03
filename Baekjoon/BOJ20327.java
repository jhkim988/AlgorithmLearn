import java.io.*;
import java.util.*;

public class BOJ20327 {
  static int N;
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
    N = Integer.parseInt(st.nextToken());
    int numOper = Integer.parseInt(st.nextToken());
    int len = 1 << N;
    int[][] arr = new int[len][len];
    for (int i = 0; i < len; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < len; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    ArrayList<ArrayList<ArrayList<Pair>>> idxArrayList = new ArrayList<>();
    init(idxArrayList);
    for (int i = 0; i < numOper; i++) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int level = Integer.parseInt(st.nextToken());
      operator(type, level, idxArrayList, arr);
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      sb.append(arr[i][0]);
      for (int j = 1; j < len; j++) {
        sb.append(' ').append(arr[i][j]);
      }
      sb.append('\n');
    }
    bw.write(sb.toString());
    bw.flush();
  }
  static void init(ArrayList<ArrayList<ArrayList<Pair>>> idxArrayList) {
    for (int i = 0; i < N; i++) {
      int arrLen = 1 << (N - i);
      int cellSize = 1 << i;
      ArrayList<ArrayList<Pair>> rows = new ArrayList<>();
      idxArrayList.add(rows);
      for (int k = 0; k < arrLen; k++) {
        ArrayList<Pair> row = new ArrayList<>();
        rows.add(row);
        for (int l = 0; l < arrLen; l++) {
          row.add(new Pair(k * cellSize, l * cellSize, cellSize));
        }
      }
    }
  }
  static void operator(int type, int level, ArrayList<ArrayList<ArrayList<Pair>>> idxArrayList, int[][] arr) {
    ArrayList<ArrayList<Pair>> doubleArr = idxArrayList.get(level);
    switch (type) {
      case 1: {
        for (ArrayList<Pair> rows : doubleArr) {
          for (Pair cell : rows) {
            int startRow = cell.row;
            int startCol = cell.col;
            int size = cell.len;
            for (int i = 0; i < size/2; i++) {
              for (int j = 0; j < size; j++) {
                int tmp = arr[i + startRow][j + startCol];
                arr[i + startRow][j + startCol] = arr[startRow + size - 1 - i][j + startCol];
                arr[startRow + size - 1 - i][j + startCol] = tmp;
              }
            }
          }
        }
        break;
      }
      case 2: {
        for (ArrayList<Pair> rows : doubleArr) {
          for (Pair cell : rows) {
            int startRow = cell.row;
            int startCol = cell.col;
            int size = cell.len;
            for (int i = 0; i < size; i++) {
              for (int j = 0; j < size/2; j++) {
                int tmp = arr[i + startRow][j + startCol];
                arr[i + startRow][j + startCol] = arr[i + startRow][startCol + size - 1 - j];
                arr[i + startRow][startCol + size - 1 - j] = tmp;
              }
            }
          }
        }
        break;
      }
      case 3: {
        for (ArrayList<Pair> rows : doubleArr) {
          for (Pair cell : rows) {
            int startRow = cell.row;
            int startCol = cell.col;
            int size = cell.len;
            for (int i = 0; i < size/2; i++) {
              for (int j = 0; j < size/2; j++) {
                int tmp = arr[startRow + i][startCol + j];
                arr[startRow + i][startCol + j] = arr[startRow + size - 1 - j][startCol + i];
                arr[startRow + size - 1 - j][startCol + i] = arr[startRow + size - 1 - i][startCol + size - 1 - j];
                arr[startRow + size - 1 - i][startCol + size - 1 - j] = arr[startRow + j][startCol + size - 1 - i];
                arr[startRow + j][startCol + size - 1 - i] = tmp;
              }
            }
          }
        }
        break;
      }
      case 4: {
        for (ArrayList<Pair> rows : doubleArr) {
          for (Pair cell : rows) {
            int startRow = cell.row;
            int startCol = cell.col;
            int size = cell.len;
            for (int i = 0; i < size/2; i++) {
              for (int j = 0; j < size/2; j++) {
                int tmp = arr[startRow + i][startCol + j];
                arr[startRow + i][startCol + j] = arr[startRow + j][startCol + size - 1 - i];
                arr[startRow + j][startCol + size - 1 - i] = arr[startRow + size - 1 - i][startCol + size - 1 - j];
                arr[startRow + size - 1 - i][startCol + size - 1 - j] = arr[startRow + size - 1 - j][startCol + i];
                arr[startRow + size - 1 - j][startCol + i] = tmp;
              }
            }
          }
        }
        break;
      }
      case 5: {
        int size = doubleArr.size();
        for (int i = 0; i < size/2; i++) {
          for (int j = 0; j < size; j++) {
            Pair tmp1 = doubleArr.get(i).get(j);
            Pair tmp2 = doubleArr.get(size - 1 - i).get(j);
            int cellSize = tmp1.len;
            for (int k = 0; k < cellSize; k++) {
              for (int l = 0; l < cellSize; l++) {
                int tmp = arr[tmp1.row + k][tmp1.col + l];
                arr[tmp1.row + k][tmp1.col + l] = arr[tmp2.row + k][tmp2.col + l];
                arr[tmp2.row + k][tmp2.col + l] = tmp;
              }
            }
          }
        }
        break;
      }
      case 6: {
        int size = doubleArr.size();
        for (int i = 0; i < size; i++) {
          for (int j = 0; j < size/2; j++) {
            Pair tmp1 = doubleArr.get(i).get(j);
            Pair tmp2 = doubleArr.get(i).get(size - 1 - j);
            int cellSize = tmp1.len;
            for (int k = 0; k < cellSize; k++) {
              for (int l = 0; l < cellSize; l++) {
                int tmp = arr[tmp1.row + k][tmp1.col + l];
                arr[tmp1.row + k][tmp1.col + l] = arr[tmp2.row + k][tmp2.col + l];
                arr[tmp2.row + k][tmp2.col + l] = tmp;
              }
            }
          }
        }
        break;
      }
      case 7: {
        int size = doubleArr.size();
        for (int i = 0; i < size/2; i++) {
          for (int j = 0; j < size/2; j++) {
            Pair tmp1 = doubleArr.get(i).get(j);
            Pair tmp2 = doubleArr.get(size - 1 - j).get(i);
            Pair tmp3 = doubleArr.get(size - 1 - i).get(size - 1 - j);
            Pair tmp4 = doubleArr.get(j).get(size - 1 - i);
            int cellSize = tmp1.len;
            for (int k = 0; k < cellSize; k++) {
              for (int l = 0; l < cellSize; l++) {
                int tmp = arr[tmp1.row + k][tmp1.col + l];
                arr[tmp1.row + k][tmp1.col + l] = arr[tmp2.row + k][tmp2.col + l];
                arr[tmp2.row + k][tmp2.col + l] = arr[tmp3.row + k][tmp3.col + l];
                arr[tmp3.row + k][tmp3.col + l] = arr[tmp4.row + k][tmp4.col + l];
                arr[tmp4.row + k][tmp4.col + l] = tmp;
              }
            }
          }
        }
        break;
      }
      default: {
        int size = doubleArr.size();
        for (int i = 0; i < size/2; i++) {
          for (int j = 0; j < size/2; j++) {
            Pair tmp1 = doubleArr.get(i).get(j);
            Pair tmp2 = doubleArr.get(j).get(size - 1 - i);
            Pair tmp3 = doubleArr.get(size - 1 - i).get(size - 1 - j);
            Pair tmp4 = doubleArr.get(size - 1 - j).get(i);
            int cellSize = tmp1.len;
            for (int k = 0; k < cellSize; k++) {
              for (int l = 0; l < cellSize; l++) {
                int tmp = arr[tmp1.row + k][tmp1.col + l];
                arr[tmp1.row + k][tmp1.col + l] = arr[tmp2.row + k][tmp2.col + l];
                arr[tmp2.row + k][tmp2.col + l] = arr[tmp3.row + k][tmp3.col + l];
                arr[tmp3.row + k][tmp3.col + l] = arr[tmp4.row + k][tmp4.col + l];
                arr[tmp4.row + k][tmp4.col + l] = tmp;
              }
            }
          }
        }
        break;
      }
    }
  }
}
