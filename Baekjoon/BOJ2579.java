import java.io.*;

public class BOJ2579 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numStair = Integer.parseInt(br.readLine());
      int[][] maxScores = new int[numStair][2];
      int input;
      
      for (int i = 0; i < numStair; i++) {
        input = Integer.parseInt(br.readLine());
        if (i == 0) {
          maxScores[i][0] = input;
        } else if (i == 1) {
          maxScores[i][0] = input + maxScores[i - 1][0];
          maxScores[i][1] = input;
        } else {
          maxScores[i][0] = maxScores[i - 1][1] + input;
          maxScores[i][1] = Math.max(maxScores[i - 2][0], maxScores[i - 2][1]) + input;
        }
      }
      bw.write(Math.max(maxScores[numStair - 1][0], maxScores[numStair - 1][1]) + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
