import java.io.*;
import java.util.*;

public class BOJ17608 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    Stack<Integer> stk = new Stack<>();
    while (N-- > 0) {
      int input = Integer.parseInt(br.readLine());
      while (!stk.isEmpty() && stk.peek() <= input) {
        stk.pop();        
      }
      stk.push(input);
    }
    bw.write(stk.size() + "\n");
    bw.flush();
  }
}
