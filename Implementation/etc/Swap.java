public class Swap {
  public static void main(String[] args) {
    int a = 3, b = 5;
    b = swap(a, a=b);
    System.out.println(a + ", " + b);
  }
  private static <T> T swap(T a, T b) {
    return a;
  }  
}
