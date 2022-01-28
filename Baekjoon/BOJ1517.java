import java.io.*;
import java.util.*;

public class BOJ1517 {
  static long numCall = 0;
  static int[] data;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    data = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }
    recur(0, N - 1);
    bw.write(Long.toString(numCall));
    bw.newLine();
    bw.flush();
  }
  static void recur(int lo, int hi) {
    // System.out.println("lo: " + lo + " ~ hi: " + hi);
    // System.out.println(Arrays.toString(data));
    if (lo == hi) return;
    if (lo + 1 == hi) {
      if (data[lo] > data[hi]) {
        numCall++;
        int tmp = data[lo];
        data[lo] = data[hi];
        data[hi] = tmp;
      }
      return;
    }
    int mid = (lo + hi) / 2;
    recur(lo, mid);
    recur(mid + 1, hi);
    int[] tmp = new int[mid + 1 - lo];
    System.arraycopy(data, lo, tmp, 0, mid + 1 - lo);
    // System.out.println("lo:" + lo + " / mid: " + mid + " / hi: " + hi);
    // System.out.println(Arrays.toString(tmp));
    int ptr1 = 0; // tmp
    int ptr2 = mid + 1; // data
    int ptr = lo;
    // System.out.println("ptr1: " + ptr1 + " / ptr2: " + ptr2);
    while (ptr1 < mid + 1 - lo && ptr2 <= hi) {
      if (tmp[ptr1] > data[ptr2]) {
        numCall += mid + 1 - lo - ptr1;
        data[ptr] = data[ptr2];
        ptr2++;
      } else {
        data[ptr] = tmp[ptr1];
        ptr1++;
      }
      ptr++;
    }
    while (ptr1 < mid + 1 - lo) {
      data[ptr] = tmp[ptr1];
      ptr1++;
      ptr++;
    }
    while (ptr2 <= hi) {
      data[ptr] = data[ptr2];
      ptr2++;
      ptr++;
    }
  }
}