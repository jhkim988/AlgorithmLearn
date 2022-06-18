import java.io.*;
import java.util.*;

public class BOJ11501 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      long[] arr = new long[n];
      for (int i = 0; i < n; i++) {
        arr[i] = Long.parseLong(st.nextToken());
      }
      Deque<Integer> deq = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        while (!deq.isEmpty() && arr[deq.peekLast()] <= arr[i]) {
          deq.removeLast();
        }
        deq.addLast(i);
      }
      long[] psum = new long[n];
      psum[0] = arr[0];
      for (int i = 1; i < n; i++) {
        psum[i] = psum[i-1] + arr[i];
      }
      int prev = deq.removeFirst();
      long answer = prev == 0 ? 0 : prev*arr[prev] - psum[prev-1];
      while (!deq.isEmpty()) {
        int crnt = deq.removeFirst();
        answer += (crnt-prev-1)*arr[crnt] - psum[crnt-1] + psum[prev];
        prev = crnt;
      }
      bw.write(Long.toString(answer));
      bw.newLine();
    }
    bw.flush();
  }
}
