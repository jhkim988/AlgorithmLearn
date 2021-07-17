import java.util.Scanner;

public class BOJ10872 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    System.out.println(factorial(scn.nextInt()));
    scn.close();
  }
  static int factorial(int n) {
    if (n == 0) {
      return 1;
    }
    return factorial(n - 1) * n;
  }
}
