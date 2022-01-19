public class OverflowCheck {
  public static void main(String[] args) {
    int small = 3;
    int large = 100_000_000;
    System.out.println(overflow(small, '*', small) ? "Overflow!" : small * small);
    System.out.println(overflow(large, '*', large) ? "Overflow!" : large * large);
  }  
  static boolean overflow(int operand1, char operator, int operand2) {
    switch (operator) {
      case '+':
        return operand1 > Integer.MAX_VALUE - operand2;
      case '-':
        return operand1 > Integer.MAX_VALUE + operand2;
      case '*':
        return operand2 != 0 && operand1 > Integer.MAX_VALUE / operand2;
      default :
        return operand1 > Integer.MAX_VALUE * operand2;
      }
  }
}
