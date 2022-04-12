import java.io.*;
import java.util.*;

public class BOJ2812 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    char[] input = br.readLine().toCharArray();
    Stack<Integer> stk = new Stack<>();
    int start = 0, remainder = n-k;
    for (int i = start; i <= input.length-remainder; i++) {

    }
    while (start < input.length && remainder > 0) {
      int maxIdx = start;
      for (int i = start; i <= input.length-remainder; i++) {
        if (input[maxIdx] < input[i]) {
          stk.clear();
          stk.push(maxIdx);
        } else if (!stk.isEmpty() && stk.peek() >= input[i]) {
          stk.push(i);
        }
      }
      bw.write(input[maxIdx]);
      start = maxIdx+1;
      remainder--;
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