import java.io.*;
import java.util.*;

public class BOJ10773 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      Stack<Integer> stk = new Stack<Integer>();
      int num = Integer.parseInt(br.readLine());
      
      long sum = 0;
      int input;
      for (int i = 0; i < num; i++) {
        input = Integer.parseInt(br.readLine());
        if (input == 0) {
          sum -= stk.pop();
        } else {
          sum += input;
          stk.push(input);
        }
      }
      bw.write(sum + "\n");
      bw.flush();
    } catch (IOException e) {

    }
  }
}
