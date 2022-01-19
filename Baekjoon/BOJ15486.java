import java.io.*;
import java.util.*;

public class BOJ15486 {
  static int N;
  static int[] time, price;
  static int[] cache;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());
    time = new int[N];
    price = new int[N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      time[i] = Integer.parseInt(st.nextToken());
      price[i] = Integer.parseInt(st.nextToken());
    }
    cache = new int[N];
    // Arrays.fill(cache, -1); recur(0);
    noRecur();
    bw.write(Integer.toString(cache[0]));
    bw.newLine();
    bw.flush();
  }
  static int recur(int date) {
    if (date >= N) return 0;
    if (cache[date] != -1) return cache[date];
    int next = recur(date + 1);
    if (date + time[date] > N) return cache[date] = next;
    return cache[date] = Integer.max(price[date] + recur(date + time[date]), next);
  }
  static int noRecur() {
    if (time[N - 1] == 1) cache[N - 1] = price[N - 1];
    for (int i = N - 2; i >= 0; i--) {
      if (i + time[i] > N) cache[i] = cache[i + 1];
      else if (i + time[i] == N) cache[i] = Integer.max(price[i], cache[i + 1]);
      else cache[i] = Integer.max(price[i] + cache[i + time[i]], cache[i + 1]);
    }
    return cache[0];
  }
}
