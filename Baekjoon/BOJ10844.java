import java.util.Scanner;

public class BOJ10844 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int num = scn.nextInt();
    scn.close();
   
    System.out.println(sol(num));
  }
  static long sol(int num) {
    long[][] memo = new long[num][10]; // memo[length - 1][end]
    memo[0][0] = 0L;
    for (int i = 1; i < 10; i++) {
      memo[0][i] = 1L;
    }
    for (int i = 1; i < num; i++) {
      for (int j = 0; j < 10; j++) {
        if (j == 0) {
          memo[i][j] = memo[i - 1][j + 1];
        } else if (j == 9) {
          memo[i][j] = memo[i - 1][j - 1];
        } else {
          memo[i][j] = (memo[i - 1][j - 1] + memo[i - 1][j + 1]) % 1000000000L;
        }        
      }
    }
    long result = 0L;
    for (int i = 0; i < 10; i++) {
      result += memo[num - 1][i];
      result %= 1000000000L;
    }
    return result;
  }
}
