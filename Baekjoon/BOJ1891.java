import java.io.*;
import java.util.*;

public class BOJ1891 {
  static int numCut;
  static long range;
  private static class Pair {
    long row;
    long col;
    Pair(long row, long col) {
      this.row = row;
      this.col = col;
    }
    boolean move(long right, long up) {
      this.col += right;
      this.row -= up;
      if (this.row < 0 || this.row >= range || this.col < 0 || this.col >= range) return false;
      return true;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    numCut = Integer.parseInt(st.nextToken());
    range = 1L << numCut;
    char[] current = st.nextToken().toCharArray();
    st = new StringTokenizer(br.readLine());
    long right = Long.parseLong(st.nextToken());
    long up = Long.parseLong(st.nextToken());
    
    Pair p = getCoordi(current);
    // System.out.println(p.row + ", " + p.col);
    if (!p.move(right, up)) {
      bw.write("-1\n");
      bw.flush();
      return;
    }
    bw.write(getCode(p).toString());
    bw.flush();
  }
  static Pair getCoordi(char[] pos) {
    long startRow = 0;
    long startCol = 0;
    long endRow = (1L << numCut) - 1;
    long endCol = (1L << numCut) - 1;

    int ptr = 0;
    while (ptr < numCut && startRow < endRow && startCol < endCol) {
      // System.out.println(startRow + ", " + startCol + " : " + endRow + ", " + endCol);
      switch (pos[ptr++]) {
        case '1':
          startCol = startCol + endCol - (startCol + endCol) / 2;
          endRow = (startRow + endRow) / 2;
          break;
        case '2':
          endRow = (startRow + endRow) / 2;
          endCol = (startCol + endCol) / 2;
          break;
        case '3':
          startRow = startRow + endRow - (startRow + endRow) / 2;
          endCol = (startCol + endCol) / 2;
          break;
        case '4':
          startRow = startRow + endRow - (startRow + endRow) / 2;
          startCol = startCol + endCol - (startCol + endCol) / 2;
          break;
      }
    }
    // System.out.println(startRow + ", " + startCol + " : " + endRow + ", " + endCol);
    return new Pair(startRow, startCol);
  }
  static StringBuilder getCode(Pair p) {
    StringBuilder sb = new StringBuilder();
    long row = p.row;
    long col = p.col;
    long compare = 1L << (numCut);
    while (compare != 1L) {
      // System.out.println("compare: " + compare);
      // System.out.println("row: " + row + ", col: " + col);
      compare >>= 1L;
      if (row < compare) {
        if (col < compare) {
          sb.append(2);
        } else {
          sb.append(1);
          col -= compare;
        }
      } else {
        if (col < compare) {
          sb.append(3);
        } else {
          sb.append(4);
          col -= compare;
        }
        row -= compare;
      }
    }
    sb.append('\n');
    return sb;
  }
}
