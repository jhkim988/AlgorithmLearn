import java.io.*;
import java.util.*;

public class BOJ2493 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int[] answer = new int[n];
    Stack<Integer> stk = new Stack<>();
    stk.push(n-1);
    for (int i = n-2; i >= 0; i--) {
      while (!stk.isEmpty() && arr[stk.peek()] <= arr[i]) {
        answer[stk.pop()] = i+1;
      }
      stk.push(i);
    }
    for (int el : answer) {
      bw.write(Integer.toString(el));
      bw.write(' ');
    }
    bw.newLine();
    bw.flush();
  }
}
