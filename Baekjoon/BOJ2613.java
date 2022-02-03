import java.io.*;
import java.util.*;

public class BOJ2613 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    int max = 0;
    int sum = 0;
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      sum += arr[i];
      max = Integer.max(max, arr[i]);
    }

    int lo = max - 1; // false
    int hi = sum; // true
    while (lo + 1 < hi) {
      int mid = (lo + hi) / 2;
      if (!check(mid, arr, m)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    int maxValue = hi;
    bw.write(Integer.toString(maxValue));
    bw.newLine();
    print(maxValue, arr, m);
    bw.flush();
  }
  static boolean check(int val, int[] arr, int lim) {
    lim--;
    int sum = 0;
    for (int el : arr) {
      if (el > val) return false;
      if (sum + el > val) {
        sum = el;
        lim--;
        if (lim < 0) return false;
      } else {
        sum += el;
      }
    }
    return lim >= 0;
  }
  static void print(int maxValue, int[] arr, int m) throws IOException {
    ArrayList<Integer> store = new ArrayList<>();
    int sum = 0;
    int count = 0;
    for (int el : arr) {
      if (sum + el > maxValue) {
        store.add(count);
        sum = el;
        count = 1;
      } else {
        sum += el;
        count++;
      }
    }
    store.add(count);
    if (store.size() == m) {
      for (int n : store) {
        bw.write(Integer.toString(n));
        bw.write(' ');
      }
      bw.newLine();
    } else {
      int idx = store.size() - 1;
      int acc = store.get(idx);
      while (idx + acc < m) {
        acc += store.get(--idx);
      }
      for (int i = 0; i < idx; i++) {
        bw.write(Integer.toString(store.get(i)));
        bw.write(' ');
      }
      int numPrint = idx;
      int diff = acc + idx - m;
      if (diff != 0) {
        bw.write(Integer.toString(diff));
        bw.write(' ');
        numPrint++;
      }
      for (; numPrint < m; numPrint++) {
        bw.write("1 ");
      }
      bw.newLine();
    }
  }
}
