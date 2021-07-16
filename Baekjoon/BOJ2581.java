import java.util.Scanner;

public class BOJ2581 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int leftInterval = scn.nextInt();
    int rightInterval = scn.nextInt();
    scn.close();

    int minPrime = rightInterval + 1;
    int sumPrime = 0;
    for (int i = leftInterval; i <= rightInterval; i++) {
      if (isPrime(i)) {
        if (i < minPrime) {
          minPrime = i;
        }
        sumPrime += i;
      }
    }

    if (minPrime <= rightInterval) {
      System.out.println(sumPrime);
      System.out.print(minPrime);
    } else {
      System.out.print(-1);
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

