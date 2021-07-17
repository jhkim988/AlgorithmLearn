import java.math.BigInteger;
import java.util.Scanner;

public class BOJ15829 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int L = scn.nextInt();
    char[] input = new char[L];
    input = scn.next().toCharArray();
    scn.close();

    BigInteger result = BigInteger.ZERO;
    long r = 31;
    long M = 1234567891;

    for (int i = 0; i < L; i++) {
      int tmp = (int) input[i] - 96;
      result = result.add(new BigInteger(tmp + "").multiply(pow(r, i)));
    }
    System.out.println(result.mod(new BigInteger(M + "")));
  }
  static BigInteger pow (long base, int exp) {
    BigInteger result = BigInteger.ONE;
    for (int i = 0; i < exp; i++) {
      result = result.multiply(new BigInteger(base + ""));
    }
    return result;
  }
}
