import java.io.*;
import java.util.*;

public class BOJ16931 {
  public static void main(String[] args) throws IOException {
    int[] rowDi = {-1, 0, 1, 0};
    int[] colDi = {0, -1, 0, 1};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());
    int[][] input = new int[row][col];
    int totNum = 0;
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        input[i][j] = Integer.parseInt(st.nextToken());
        totNum += input[i][j];
      }
    }

    int adjSurface = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        adjSurface += 2 * (input[i][j] - 1);
        for (int k = 0; k < 4; k++) {
          int adjRow = i + rowDi[k];
          int adjCol = j + colDi[k];
          if (adjRow < 0 || adjRow >= row || adjCol < 0 || adjCol >= col) continue;
          adjSurface += Math.min(input[i][j], input[adjRow][adjCol]);
        }
      }
    }

    int answer = totNum * 6 - adjSurface;
    bw.write(answer + "\n");
    bw.flush();
  }
}
