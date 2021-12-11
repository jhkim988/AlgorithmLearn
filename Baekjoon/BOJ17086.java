import java.io.*;
import java.util.*;

public class BOJ17086 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int numRow = Integer.parseInt(st.nextToken());
    int numCol = Integer.parseInt(st.nextToken());
    int[][] data = new int[numRow][numCol];
    for (int i = 0; i < numRow; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < numCol; j++) {
        data[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }
}
