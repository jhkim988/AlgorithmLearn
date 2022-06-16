import java.io.*;
import java.util.*;

public class BOJ2141 {
  private static class Pair {
    int pos, num;
    Pair(int pos, int num) {
      this.pos = pos;
      this.num = num;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Pair[] arr = new Pair[n];
    long total = 0;
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int pos = Integer.parseInt(st.nextToken());
      int num = Integer.parseInt(st.nextToken());
      arr[i] = new Pair(pos, num);
      total += num;
    }
    Arrays.sort(arr, (a, b) -> {
      return a.pos != b.pos ? a.pos-b.pos : a.num-b.num;
    });
    
    long sum = 0;
    for (int i = 0; i < n; i++) {
      if (sum+arr[i].num < total/2) {
        sum += arr[i].num;
      } else {
        bw.write(Integer.toString(arr[i].pos));
        bw.flush();
        return;
      }
    }
  }
}
