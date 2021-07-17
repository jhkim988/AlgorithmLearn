import java.util.Scanner;

public class BOJ2231 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int input = scn.nextInt();
    scn.close();
    
    int result = 0;
    for (int i = 0; i < input; i++) {
      if (input == decompSum(i)) {
        result = i;
        break;
      }
    }
    System.out.println(result);
  }
  static int decompSum(int num) {
    int result = num;
    while (num != 0) {
      result += num % 10;
      num /= 10;
    }
    return result;
  }
}
