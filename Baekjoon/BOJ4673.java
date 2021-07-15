public class BOJ4673 {
  public static void main(String[] args) {
    for (int i = 1; i <= 10000; i++) {
      if (isSelfNumber(i)) {
        System.out.println(i);
      }
    }
  }
  static int d(int num) {
    int tmp = num;
    while(tmp > 0) {
      num += tmp % 10;
      tmp /= 10;
    }
    return num;
  }
  static boolean isSelfNumber(int num) {
    for (int i = 1; i < num; i++) {
      if (d(i) == num) {
        return false;
      }
    }
    return true;
  }
}
