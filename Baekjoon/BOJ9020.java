import java.util.Scanner;

public class BOJ9020 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int numTest = scn.nextInt();
    for (int i = 0; i < numTest; i++) {
      printGbPartition(scn.nextInt());
    }
    scn.close();
  }
  static void printGbPartition(int num) {
    int tmp = num / 2;
    for (int i = tmp; i >= 2; i--) {
      if (isPrime(i) && isPrime(num - i)) {
        System.out.println(i + " " + (num - i));
        break;
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
