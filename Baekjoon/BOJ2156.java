import java.io.*;

public class BOJ2156 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numWine = Integer.parseInt(br.readLine());
      int[][] sumData = new int[numWine][3];
      int tmp;
      for (int i = 0; i < numWine; i++) {
        tmp = Integer.parseInt(br.readLine());
        if (i == 0) {
          sumData[i][0] = tmp;
        } else if (i == 1) {
          sumData[i][0] = sumData[i - 1][0] + tmp;
          sumData[i][1] = tmp;
          sumData[i][2] = sumData[i - 1][0];
        } else if (i == 2) {
          sumData[i][0] = sumData[i - 1][1] + tmp;
          sumData[i][1] = Math.max(sumData[i - 2][0], sumData[i - 2][1]) + tmp;
          sumData[i][2] = sumData[i - 1][0];
        } else {
          sumData[i][0] = Math.max(sumData[i - 1][1], sumData[i - 1][2]) + tmp;
          sumData[i][1] = Math.max(sumData[i - 2][0], Math.max(sumData[i - 2][1], sumData[i - 2][2])) + tmp;
          sumData[i][2] = Math.max(sumData[i - 1][0], Math.max(sumData[i - 1][1], sumData[i - 1][2]));
        }
      }

      bw.write(Math.max(sumData[numWine - 1][0], Math.max(sumData[numWine - 1][1], sumData[numWine - 1][2])) + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
