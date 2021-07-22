import java.util.Random;

public class BOJ2579_Try {
  public static void main(String[] args) {
    int len = 5;
    int[] data = new int[len];
    Random r = new Random();

    while (true) {
      for (int i = 0; i < len; i++) {
        data[i] = r.nextInt(10) + 1;
      }
      if (mySol(data, len) != sol(data, len)) {
        for (int i = 0; i < len; i++) {
          System.out.print(data[i] + " ");
        }
        System.out.println(": " + mySol(data, len) + " " + sol(data, len));
      }
    }
  }
  static int mySol(int[] data, int len) {
    int numStair = len;
    int[] maxScores = new int[numStair];
    int data0 = -1;
    int data1 = -1;
    int input;
    boolean prev = false;
    for (int i = 0; i < numStair; i++) {
      input = data[i];
      if (i == 0) {
        data0 = input;
        maxScores[i] = input;
      } else if (i == 1) {
        data1 = input;
        maxScores[i] = maxScores[i - 1] + input;
        prev = true;
      } else if (i == 2) {
        if (data0 < data1) {
          maxScores[i] = data1 + input;
          prev = true;
        } else {
          maxScores[i] = data0 + input;
          prev = false;
        }
      } else {
        if (prev) {
          maxScores[i] = input + maxScores[i - 2];
          prev = false;
        } else {
          if (maxScores[i - 1] > maxScores[i - 2]) {
            maxScores[i] = input + maxScores[i - 1];
            prev = true;
          } else {
            maxScores[i] = input + maxScores[i - 2];
            prev = false;
          }
        }
      }
    }
    return maxScores[numStair - 1];
  }
  static int sol(int[] data, int len) {
    int numStair = len;
    int[][] maxScores = new int[numStair][2];
    int input;
    
    for (int i = 0; i < numStair; i++) {
      input = data[i];
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
    return Math.max(maxScores[numStair - 1][0], maxScores[numStair - 1][1]);
  }
}
