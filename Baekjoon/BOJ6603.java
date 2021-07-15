import java.util.Scanner;

public class BOJ6603 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    while (lineExe(scn.nextLine().split(" ")));
  }

  static boolean lineExe(String[] input) {
    int k = Integer.parseInt(input[0]);
    if (k == 0) {
      return false;
    }

    int[] lottoSet = new int[k];
    for (int i = 0; i < k; i++) {
      lottoSet[i] = Integer.parseInt(input[i + 1]);
    }

    int numPick = 6;
    lotto(k, numPick, lottoSet);
    return true;
  }

  static void lotto(int numSet, int numPick, int[] lottoSet) {
    for (int i = 0; i <= numSet - numPick; i++) {
      lotto(numSet - numPick, i, lottoSet[i] + " ", 1, numPick, lottoSet);
    }
    System.out.println();
  }

  static void lotto(int endValueOfLayer, int prevTail, String result, int length, int numPick, int[] lottoSet) {
    // System.out.println("lotto call: " + endValueOfLayer + ", " + prevTail + ", " + result + ", " + length);
    for (int i = prevTail + 1; i <= (endValueOfLayer + 1) && i < lottoSet.length; i++) {
      lotto(endValueOfLayer + 1, i, result + lottoSet[i] + " ", length + 1, numPick, lottoSet);
    }

    if (length == numPick) {
      System.out.println(result);
      return;
    }
  }
}
