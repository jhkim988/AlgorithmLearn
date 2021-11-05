import java.io.*;
import java.util.*;

public class BOJ2343 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[] data = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    int sol = answer(N, M, data);
    bw.write(sol + "\n");
    bw.flush();
  }

  static int answer(int N, int M, int[] data) {
    int max = 0;
    int sum = 0;
    for (int num : data) {
      if (max < num) max = num;
      sum += num;
    }
    int lo = max;
    int hi = sum;
    while (lo < hi) {
      int mid = (lo + hi) / 2;
      boolean flag = possible(N, M, data, mid);
      // System.out.println("lo: " + lo + ", hi: " + hi + ", mid: " + mid + ", flag: " + flag);
      if (flag) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return hi;
  }
  static boolean possible(int N, int M, int[] data, int size) {
    int count = 0;
    int ptr = 0;
    // System.out.println("size: " + size);
    while (ptr < N) {
      // System.out.println("ptr: " + ptr);
      int sum = 0;
      while (ptr < N && sum + data[ptr] <= size) {
        sum += data[ptr++];
      }
      count++;
    }
    return count <= M;
  }
}
