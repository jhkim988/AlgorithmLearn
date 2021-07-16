import java.util.Scanner;

public class BOJ4948 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int input;
    while((input = scn.nextInt()) != 0) {
      System.out.println(numPrime(input));
    }
    scn.close();
  }
  static int numPrime(int num) {
    // num < prime <= 2*num
    int count = 0;
    for (int i = num + 1; i <= 2 * num; i++) {
      if (isPrime(i)) {
        count++;
      }
    }
    return count;
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
