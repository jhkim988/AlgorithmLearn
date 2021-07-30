import java.io.*;

public class BOJ11279 {
  private static class MaxPQ {
    long[] arr; // root node = arr[1];
    int ptr;
    MaxPQ(int N) {
      arr = new long[N + 1];
      ptr = 0;
    }
    private void swim(int k) {
      while (k > 1 && arr[k/2] < arr[k]) {
        long tmp = arr[k/2];
        arr[k/2] = arr[k];
        arr[k] = tmp;
        k /= 2;
      }
    }
    private void sink(int k) {
      while (2 * k <= ptr) {
        int j = 2 * k;
        if (j < ptr && arr[j] < arr[j + 1]) {
          j++;
        }
        if (arr[k] >= arr[j]) {
          break;
        }
        long tmp = arr[k];
        arr[k] = arr[j];
        arr[j] = tmp;
        k = j;
      }
    }
    void insert(long x) {
      arr[++ptr] = x;
      swim(ptr);
    }
    long delMax() {
      if (ptr == 0) {
        return 0;
      }
      long max = arr[1];
      long tmp = arr[1];
      arr[1] = arr[ptr];
      arr[ptr--] = tmp;
      sink(1);
      arr[ptr + 1] = 0;
      return max;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    MaxPQ pq = new MaxPQ(N);

    for (int i = 0; i < N; i++) {
      long input = Long.parseLong(br.readLine());
      if (input == 0) {
        bw.write(pq.delMax() + "\n");
      } else {
        pq.insert(input);
      }
    }
    bw.flush();
  }
}
