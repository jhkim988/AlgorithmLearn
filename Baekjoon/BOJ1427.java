import java.util.Scanner;

public class BOJ1427 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    char[] input = scn.nextLine().toCharArray();
    scn.close();

    long[] count = new long[10];
    for (int i = 0; i < input.length; i++) {
      count[input[i] - '0']++;
    }
    String result = "";
    for (int i = 9; i >= 0; i--) {
      for (int j = 0; j < count[i]; j++) {
        result += i;
      }
    }
    System.out.println(result);
  }
}
