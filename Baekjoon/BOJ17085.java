import java.io.*;
import java.util.*;

public class BOJ17085 {
  static int numRow;
  static int numCol;
  static char[][] data;
  static int[] rowDi = {-1, 0, 1, 0};
  static int[] colDi = {0, -1, 0, 1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    numRow = Integer.parseInt(st.nextToken());
    numCol = Integer.parseInt(st.nextToken());
    data = new char[numRow][];
    for (int i = 0; i < numRow; i++) {
      data[i] = br.readLine().toCharArray();
    }
    
    int max = 0;
    for (int i = 0; i < numRow; i++) {
      for (int j = 0; j < numCol; j++) {
        if (data[i][j] == '.') continue;
        max = Math.max(max, area(i, j));
      }
    }

    bw.write(max + "\n");
    bw.flush();
  }
  static int area(int rowIdx, int colIdx) {
    int ptr1 = rowIdx * numCol + colIdx;
    int size1 = getSize(rowIdx, colIdx);
    int max = 0;
    for (int i = 0; i < numRow; i++) {
      for (int j = 0; j < numCol; j++) {
        if (data[i][j] == '.') continue;
        int ptr2 = i * numCol + j;
        if (ptr1 >= ptr2) continue;
        int val = 0;
        int size2 = getSize(i, j);
        for (int k = size1; k >= 0; k--) {
          for (int l = size2; l >= 0; l--) {
            // System.out.println(rowIdx + ", " + colIdx + "(" + k + ")" + " / " + i + ", " + j  + "(" + l + ")" + ": " + possible(rowIdx, colIdx, k, i, j, l));
            int tmp = (4 * k + 1) * (4 * l + 1);
            if (tmp <= val) continue;
            if (possible(rowIdx, colIdx, k, i, j, l)) {
              val = Math.max(val, tmp);
            }
          }
        }
        max = Math.max(max, val);
      }
    }
    return max;
  }
  static int getSize(int rowIdx, int colIdx) {
    int size = 0;
    while (true) {
      size++;
      for (int i = 0; i < 4; i++) {
        int adjRow = rowIdx + size * rowDi[i];
        int adjCol = colIdx + size * colDi[i];
        if (adjRow < 0 || adjRow >= numRow || adjCol < 0 || adjCol >= numCol) return size - 1;
        if (data[adjRow][adjCol] != '#') return size - 1;
      }
    }
  }
  static boolean possible(int r1, int c1, int s1, int r2, int c2, int s2) {
    if (r2 < r1 - s1) {
      if (c2 < c1 - s1) return true;
      else if (c2 == c1) return r2 + s2 < r1 - s1;
      else if (c2 <= c1 + s1) return r2 + s2 < r1;
      else return true;
    } else if (r2 < r1) {
      if (c2 < c1 - s1) return c2 + s2 < c1;
      else if (c2 < c1) return c2 + s2 < c1 && r2 + s2 < r1;
      else if (c2 == c1) return false;
      else if (c2 <= c1 + s1) return c1 < c2 - s2 && r2 + s2 < r1;
      else return c1 < c2 - s2; 
    } else if (r2 == r1) {
      if (c1 < c2) return c1 + s1 < c2 - s2;
      else return c2 + s2 < c1 - s1;
    } else if (r2 <= r1 + s1) {
      if (c2 < c1 - s1) return c2 + s2 < c1;
      else if (c2 < c1) return c2 + s2 < c1 && r1 < r2 - s2;
      else if (c2 == c1) return false;
      else if (c2 <= c1 + s1) return c1 < c2 - s2 && r1 < r2 - s2;
      else return c1 < c2 - s2; 
    } else {
      if (c2 < c1 - s2) return true;
      else if (c2 == c1) return r1 + s1 < r2 - s2;
      else if (c2 <= c1 + s1) return r1 < r2 - s2;
      else return true;
    }
  }
}
