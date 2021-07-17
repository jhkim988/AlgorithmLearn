import java.util.Scanner;

public class BOJ1436 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    scn.close();

    System.out.print(nthNum(N));
  }
  static int nthNum(int num) {
    int count = 0;
    int result = 99;
    while(count < num) {
      result++;
      if (isTNum(result)) {
        count++;
      }      
    }
    return result;
  }
  static boolean isTNum(int num) {
    int[] data = new int[(num + "").length()];

    for(int i = 0; i < data.length; i++) {
      data[i] = num % 10;
      num /= 10;
    }

    for (int i = 0; i < data.length - 2; i++) {
      if (data[i] == 6 && data[i + 1] == 6 && data[i + 2] == 6) {
        return true;
      }
    }
    return false;
  }
}
