import java.io.*;
import java.util.Stack;

public class BOJ1874 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuffer sb = new StringBuffer();

      int num = Integer.parseInt(br.readLine());
      Stack<Integer> stk = new Stack<>();
      int[] data = new int[num];

      for (int i = 0; i < num; i++) {
        data[i] = Integer.parseInt(br.readLine());
      }

      int ptrData = 0;
      int j = 0;
      int pop;
      int ptrCheck = 0;
      boolean flag = true;
      for (int i = 1; i <= num; i++) {
        // input
        for (j = i; j <= data[ptrData]; j++) {
          stk.push(j);
          sb.append("+\n");
        }
        pop = stk.pop();
        if (data[ptrCheck++] != pop) {
          flag = false;
          break;
        }
        sb.append("-\n");
        ptrData++;
        i = j - 1;
      }
    
      
      while (!stk.isEmpty()) {
        pop = stk.pop();
        if (pop != data[ptrCheck++]) {
          flag = false;
          break;
        }
        sb.append("-\n");
      }

      if (!flag) {
        System.out.print("NO");
      } else {
        System.out.print(sb);
      }
    
    } catch (IOException e) {

    }
  }
}
