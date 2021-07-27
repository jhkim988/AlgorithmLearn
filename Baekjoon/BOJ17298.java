import java.io.*;
import java.util.*;

public class BOJ17298 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    Stack<Integer> stk = new Stack<>();

    int num = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] data = new int[num];
    int[] NGE = new int[num]; // NGE(i) = NGE[i + 1]
    for (int i = 0; i < num; i++) {
      data[i] = Integer.parseInt(st.nextToken());
      NGE[i] = - 1;
    }

    stk.push(0);
    for (int i = 1; i < num; i++) {
      while (!stk.empty()) {
        if (data[stk.peek()] < data[i]) {
          NGE[stk.pop()] = data[i];
        } else {
          break;
        }
      }
      stk.push(i);
    }

    for (int i = 0; i < num; i++) {
      bw.write(NGE[i] + " ");
    }
    bw.flush();
  }
}
