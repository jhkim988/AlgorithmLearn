import java.io.*;
import java.util.*;

public class BOJ1891 {
  static int numCut;
  private static class Pair {
    long row;
    long col;
    Pair(long row, long col) {
      this.row = row;
      this.col = col;
    }
    void move(long right, long up) {
      this.row += right;
      this.col += up;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    numCut = Integer.parseInt(st.nextToken());
    char[] current = st.nextToken().toCharArray();
    st = new StringTokenizer(br.readLine());
    long right = Long.parseLong(st.nextToken());
    long up = Long.parseLong(st.nextToken());
    
    Pair p = getCoordi(current);
    System.out.println(p.row + ", " + p.col);
    p.move(right, up);
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
      System.out.println(startRow + ", " + startCol + " : " + endRow + ", " + endCol);
      switch (pos[ptr++]) {
        case '1':
          startCol = endCol - (startCol + endCol) / 2;
          endRow = (startRow + endRow) / 2;
          break;
        case '2':
          endRow = (startRow + endRow) / 2;
          endCol = (startCol + endCol) / 2;
          break;
        case '3':
          startRow = endRow - (startRow + endRow) / 2;
          endCol = (startCol + endCol) / 2;
          break;
        case '4':
          startRow = endRow - (startRow + endRow) / 2;
          startCol = endCol - (startCol + endCol) / 2;
          break;
      }
    }
    System.out.println(startRow + ", " + startCol + " : " + endRow + ", " + endCol);
    return new Pair(startRow, startCol);
  }
  static StringBuilder getCode(Pair p) {
    StringBuilder sb = new StringBuilder();
    long row = p.row;
    long col = p.col;
    long compare = 1L << numCut;
    while (compare == 1L) {
      compare >>= 1;
      if (row < compare) {
        if (col < compare) {
          sb.append(2);
        } else {
          sb.append(1);
        }
      } else {
        if (col < compare) {
          sb.append(3);
        } else {
          sb.append(4);
        }
      }
    }
    sb.append('\n');
    return sb;
  }
}
