import java.io.*;
import java.util.*;

public class BOJ14719 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    int[] height = new int[w];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < w; i++) {
      height[i] = Integer.parseInt(st.nextToken());
    }
    Stack<Integer> stk = new Stack<>();
    int answer = 0;
    for (int i = 0; i < w; i++) {
      if (stk.isEmpty()) {
        stk.add(i);
        continue;
      }
      int peek = stk.peek();
      if (height[peek] <= height[i]) {
        int left = -1;
        int bottom = peek;
        while (!stk.isEmpty() && height[peek] <= height[i]) {
          bottom = stk.pop();
          if (!stk.isEmpty()) {
            peek = left = stk.peek();
            int hh = Integer.min(height[left], height[i]) - height[bottom];
            answer += hh * (i - left - 1);
          }
        }
      }
      stk.add(i);
    }
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
}
