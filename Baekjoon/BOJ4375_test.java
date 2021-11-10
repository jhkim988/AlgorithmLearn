public class BOJ4375_test {
  public static void main(String[] args) {
    for (int i = 1; i <= 10000; i++) {
      if (BOJ4375.answer(i) != new BOJ4375_sol.Mathematics_4375().process(i)) {
        System.out.println(i);
        break;
      }
    }
  }
}
