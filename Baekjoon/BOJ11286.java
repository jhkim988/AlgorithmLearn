import java.io.*;

public class BOJ11286 {
  private static class MinPQ {
    int[] arr;
    int ptr;
    MinPQ(int N) {
      arr = new int[N];
      ptr = 0;
    }
    int compare(int idx1, int idx2) {
      if (Math.abs(arr[idx1]) > Math.abs(arr[idx2])) {
        return 1;
      } else if (Math.abs(arr[idx1]) < Math.abs(arr[idx2])) {
        return - 1;
      } else {
        if (arr[idx1] > arr[idx2]) {
          return 1;
        } else if (arr[idx1] < arr[idx2]) {
          return - 1;
        } else {
          return 0;
        }
      }
    }
    void swim(int k) {
      while (k > 1 && compare(k, k/2) < 0) {
        int tmp = arr[k];
        arr[k] = arr[k/2];
        arr[k/2] = tmp;
        k /= 2;
      }
    }
    void sink(int k) {
      while (2 * k <= ptr) {
        int j = 2 * k;
        if (j < ptr && compare(j, j + 1) > 0) {
          j = 2 * k + 1;
        }
        if (compare(j, k) >= 0) {
          break;
        }
        int tmp = arr[k];
        arr[k] = arr[j];
        arr[j] = tmp;
        k = j;
      }
    }
    void insert(int x) {
      arr[++ptr] = x;
      swim(ptr);
    }
    int delMin() {
      if (ptr == 0) {
        return 0;
      }
      int min = arr[1];
      int tmp = arr[1];
      arr[1] = arr[ptr];
      arr[ptr] = tmp;
      ptr--;
      sink(1);
      return min;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    MinPQ pq = new MinPQ(N);
    for (int i = 0; i < N; i++) {
      int input = Integer.parseInt(br.readLine());
      if (input == 0) {
        bw.write(pq.delMin() + "\n");
      } else {
        pq.insert(input);
      }
    }
    bw.flush();
  }
}
