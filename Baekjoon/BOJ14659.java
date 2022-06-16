import java.io.*;
import java.util.*;

public class BOJ14659 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    // int max = useStack(n, arr);
    int max = useGreedy(n, arr);
    bw.write(Integer.toString(max));
    bw.flush();
  }
  static int useStack(int n, int[] arr) {
    int[] val = new int[n];
    Stack<Integer> stk = new Stack<>();
    for (int i = n-1; i >= 0; i--) {
      if (stk.isEmpty()) {
        val[i] = 0;
        stk.push(i);
      } else {
        while (!stk.isEmpty() && arr[i] > arr[stk.peek()]) {
          val[i] += val[stk.pop()]+1;
        }
        stk.push(i);
      }
    }
    int max = 0;
    for (int i = 0; i < n; i++) {
      if (max < val[i]) max = val[i];
    }
    return max;
  }
  static int useGreedy(int n, int[] arr) {
    int max = 0;
    int ptr = 0;
    while (ptr < n) {
      int count = 0;
      int i = 1;
      while (ptr+i < n && arr[ptr] > arr[ptr+i]) {
        count++;
        i++;
      }
      ptr = ptr+i;
      if (max < count) max = count;
    }
    return max;
  }
}
