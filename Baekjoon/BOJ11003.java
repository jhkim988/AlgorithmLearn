import java.io.*;
import java.util.*;

public class BOJ11003 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int[] input = new int[N];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());

    Deque<Integer> deq = new LinkedList<>(); // index

    bw.write(Integer.toString(input[0]));
    deq.addFirst(0);
    for (int i = 1; i < N; i++) {
      while (!deq.isEmpty() && input[deq.getFirst()] > input[i]) deq.removeFirst();
      deq.addFirst(i);
      if (!deq.isEmpty() && i - deq.getLast() >= L) deq.removeLast();
      bw.write(' ');
      bw.write(Integer.toString(input[deq.getLast()]));
    }
    bw.newLine();
    bw.flush();
  }
}
