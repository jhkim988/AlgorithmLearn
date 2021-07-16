import java.util.Scanner;

public class BOJ1929 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int leftIntv = scn.nextInt();
    int rightIntv = scn.nextInt();
    scn.close();

    for (int i = leftIntv; i <= rightIntv; i++) {
      if (isPrime(i)) {
        System.out.println(i);
      }
    }
  }
  static boolean isPrime(int num) {
    if (num == 1) {
      return false;
    }
    if (num < 4) {
      // 2, 3
      return true;
    }

    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
}
