import java.io.*;
import java.util.*;

public class BOJ4902 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    String input = br.readLine();
    int labeling = 1;
    while (input.charAt(0) != '0') {
      StringTokenizer st = new StringTokenizer(input);
      int numLine = Integer.parseInt(st.nextToken());
      input = br.readLine();
      int[][] triangle = new int[numLine][];
      for (int i = 0; i < numLine; i++) {
        triangle[i] = new int[2 * i + 1];
        for (int j = 0; j < triangle[i].length; j++) {
          triangle[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      int[][] pSum = new int[numLine][];
      for (int i = 0; i < numLine; i++) {
        pSum[i] = new int[triangle[i].length];
        pSum[i][0] = triangle[i][0];
        for (int j = 1; j < triangle[i].length; j++) {
          pSum[i][j] = pSum[i][j - 1] + triangle[i][j];
        }
      }
      int max = Integer.MIN_VALUE;
      for (int row = 0; row < numLine; row++) {
        for (int col = 0; col < triangle[row].length; col++) {
          int sum = triangle[row][col];
          max = Math.max(sum, max);
          if (col % 2 == 0) {
            for (int size = 1; row + size < numLine; size++) {
              // for (int cur = 0; cur < 2 * size + 1; cur++) sum += triangle[row + size][col + cur];
              sum += col == 0 ? pSum[row + size][col + 2 * size] : pSum[row + size][col + 2 * size] - pSum[row + size][col - 1];
              max = Math.max(sum, max);
            }            
          } else { // inverted triangle
            for (int size = 1; col < triangle[row - size].length && col - (2 * size + 1) + 1 >= 0 && row >= size; size++) {
              // for (int cur = 0; cur < 2 * size + 1; cur++) sum += triangle[row - size][col - cur];
              sum += col - 2 * size - 1 == 0 ? pSum[row - size][col] : pSum[row - size][col] - pSum[row - size][col - 2 * size - 1];
              max = Math.max(sum, max);
            }
          }
        }
      }
      bw.write((labeling++) + ". " + max + "\n");
    }
    bw.flush();
  }
}
