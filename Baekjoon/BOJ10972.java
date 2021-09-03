import java.io.*;
import java.util.*;

public class BOJ10972 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    boolean[] marked = new boolean[N + 1]; // index = node
    boolean[] hasLarger = new boolean[N]; // index = depth

    for (int i = 0; i < N; i++) {
      marked[data[i]] = true;
      for (int j = data[i] + 1; j <= N; j++) {
        if (!marked[j]) {
          hasLarger[i] = true;
          break;
        }
      }
    }

    boolean haveNext = false;
    int[] result = data.clone();
    int ptr = N - 1;
    for (int i = N - 1; i >= 0; i--) {
      if(hasLarger[i]) {
        haveNext = true;
        marked[data[i]] = false;

        int next = data[i] + 1;
        for (; next <= N; next++) {
          if (!marked[next]) {
            break;
          }
        } 
        
        marked[next] = true;
        result[i] = next;
        ptr = i + 1;
        break;
      } else {
        marked[data[i]] = false;
        result[i] = 0;
      }
    }
    if (haveNext) {
      for (int i = 1; i <= N; i++) {
        if (marked[i]) continue;
        result[ptr++] = i;
      }
      for (int r : result) {
        bw.write(r + " ");
      }
      bw.write("\n");
      bw.flush();
    } else {
      bw.write("-1\n");
      bw.flush();
    }
  }  
}
