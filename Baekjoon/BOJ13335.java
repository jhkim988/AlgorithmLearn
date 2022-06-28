import java.io.*;
import java.util.*;

public class BOJ13335 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Queue<Integer> que = new LinkedList<>();
    for (int i = 0; i < w; i++) que.add(0);
    int sum = 0;
    int time = 0;
    for (int i = 0; i < n; i++) {
      time++;
      sum -= que.poll();
      while (sum+arr[i] > l) {
        sum -= que.poll();
        que.add(0);
        time++;
      }
      sum += arr[i];
      que.add(arr[i]);
    }
    while (sum != 0) {
      time++;
      sum -= que.poll();
    }
    bw.write(Integer.toString(time));
    bw.flush();
  }
}
