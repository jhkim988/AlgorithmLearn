import java.io.*;
import java.util.*;

public class BOJ2812 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int copy = k;
    char[] input = br.readLine().toCharArray();
    Stack<Character> stk = new Stack<>();
    stk.push(input[0]);
    for (int i = 1; i < n; i++) {
      while (k > 0 && !stk.isEmpty() && stk.peek() < input[i]) {
        stk.pop();
        k--;
      }
      stk.push(input[i]);
    }
    Stack<Character> tmp = new Stack<>();
    while (!stk.isEmpty()) {
      tmp.push(stk.pop());
    }
    for (int i = 0; i < n-copy; i++) {
      bw.write(tmp.pop());
    }
    bw.newLine();
    bw.flush();
  }
  static void greedy(int start, int remainder, char[] input) throws IOException {
    if (start >= input.length) return;
    if (remainder <= 0) return;
    int maxIdx = start;
    for (int i = start; i <= input.length-remainder; i++) {
      if (input[maxIdx] < input[i]) maxIdx = i;
    }
    bw.write(input[maxIdx]);
    greedy(maxIdx+1, remainder-1, input);
  }
}