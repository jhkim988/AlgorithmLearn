import java.io.*;
import java.util.*;

public class BOJ11576 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int A = Integer.parseInt(st.nextToken());
    int B = Integer.parseInt(st.nextToken());
    int len = Integer.parseInt(br.readLine());
    int[] input = new int[len];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < len; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    long prev = 0;
    for (int ptr = len - 1; ptr >= 0; ptr--) {
      prev += input[ptr] * pow(A, len - 1 - ptr);
    }

    Stack<Long> stk = new Stack<>();
    while (prev != 0) {
      stk.push(prev % B);
      prev /= B;
    }
    StringBuilder sb = new StringBuilder();
    while (!stk.isEmpty()) {
      sb.append(stk.pop()).append(' ');
    }
    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }
  static long pow(int base, int exp) {
    if (exp == 0) {
      return 1L;
    }
    if (exp == 1) {
      return base;
    }
    long prev = pow(base, exp / 2);
    if (exp % 2 == 0) {
      return prev * prev;
    } else {
      return prev * prev * base;
    }
  }
}
