import java.io.*;
import java.util.*;

public class BOJ1935 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numOperand = Integer.parseInt(br.readLine());
    String expression = br.readLine();
    double[] val = new double[numOperand];
    for (int i = 0; i < numOperand; i++) {
      val[i] = Double.parseDouble(br.readLine());
    }

    int lenExp = expression.length();
    Stack<Double> stkVal = new Stack<>();
    for (int i = 0; i < lenExp; i++) {
      char ch = expression.charAt(i);
      if ('A' <= ch && ch <= 'Z') { // operand
        stkVal.push(val[ch - 'A']);
      } else { // operator
        double second = stkVal.pop();
        double first = stkVal.pop();
        switch (ch) {
          case '+': {
            stkVal.push(first + second);
            break;
          }
          case '-': {
            stkVal.push(first - second);
            break;
          }
          case '*': {
            stkVal.push(first * second);
            break;
          }
          case '/': {
            stkVal.push(first / second);
            break;
          }
        }
      }
    }

    bw.write(String.format("%.2f\n", stkVal.pop()));
    bw.flush();
  }
}
