import java.io.*;
import java.util.*;

public class BOJ1158 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    st = null;

    Queue<Integer> que = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      que.add(i);
    }

    StringBuilder sb = new StringBuilder();
    sb.append('<');
    while (que.size() != 1) {
      for (int i = 1; i < K; i++) {
        que.add(que.poll());
      }
      sb.append(que.poll()).append(',').append(' ');
    }
    sb.append(que.poll()).append('>').append('\n');
    bw.write(sb.toString());
    bw.flush();
  }  
}
