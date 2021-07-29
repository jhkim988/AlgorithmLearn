import java.io.*;
import java.util.*;

public class BOJ1725 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    long[] data = new long[N];
    for (int i = 0; i < N; i++) {
      data[i] = Long.parseLong(br.readLine());
    }

    Stack<Integer> stk = new Stack<>();
    long maxArea = 0;
    for (int i = 0; i < N; i++) {
      while (!stk.empty() && data[stk.peek()] > data[i]) {
        int right = i - 1;
        long height = data[stk.peek()];
        stk.pop();
        int left = 0;
        if (!stk.empty()) {
          left = stk.peek() + 1;
        }
        long tmp = (right - left + 1) * height;
        if (maxArea < tmp) {
          maxArea = tmp;
        }
      }
      stk.push(i);
    }

    while (!stk.empty()) {
      int right = N - 1;
      long height = data[stk.peek()];
      stk.pop();
      int left = 0;
      if (!stk.empty()) {
        left = stk.peek() + 1;
      }
      long tmp = (right - left + 1) * height;
      if (maxArea < tmp) {
        maxArea = tmp;
      }
    }

    System.out.println(maxArea);
  }
}
