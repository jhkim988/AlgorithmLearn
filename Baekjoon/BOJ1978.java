import java.util.Scanner;

public class BOJ1978 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int numTest = scn.nextInt();
    int count = 0;
    for (int i = 0; i < numTest; i++) {
      if (isPrime(scn.nextInt())) {
        count++;
      }
    }
    scn.close();
    System.out.print(count);
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
