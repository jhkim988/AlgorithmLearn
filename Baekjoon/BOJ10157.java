import java.io.*;
import java.util.*;

public class BOJ10157 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int c = Integer.parseInt(st.nextToken());
    int r = Integer.parseInt(st.nextToken());
    int target = Integer.parseInt(br.readLine());
    int[][] arr = new int[r][c];
    int[] rowDi = {0, -1, 0, 1};
    int[] colDi = {-1, 0, 1, 0};
    int direction = 0;
    int waiting = 1;
    int crntC = 0;
    int crntR = r-1;
    while (waiting <= r*c) {
      arr[crntR][crntC] = waiting;
      if (waiting == target) {
        bw.write(Integer.toString(crntC+1));
        bw.write(' ');
        bw.write(Integer.toString(r-crntR));
        bw.newLine();
        bw.flush();
        return;
      }
      waiting++;
      int nextR = crntR + rowDi[direction];
      int nextC = crntC + colDi[direction];
      if (nextC < 0 || nextC >= c || nextR < 0 || nextR >= r || arr[nextR][nextC] != 0) {
        direction = (direction + 1) % 4;
        crntR += rowDi[direction];
        crntC += colDi[direction];
      } else {
        crntR = nextR;
        crntC = nextC;
      }
    }
    bw.write("0\n");
    bw.flush();
  }
}
