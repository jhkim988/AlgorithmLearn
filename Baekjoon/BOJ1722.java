import java.io.*;
import java.util.*;

public class BOJ1722 {
  static long[] factorial = new long[21];
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int type = Integer.parseInt(st.nextToken());

    if (type == 1) {
      long k = Long.parseLong(st.nextToken());
      int[] arr = problem_1(n, k);
      for (int i : arr) {
        bw.write(Integer.toString(i));
        bw.write(' ');
      }
    } else {
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      } 
      long k = problem_2(n, arr);
      bw.write(Long.toString(k));
    }
    bw.flush();
  }
  static int[] problem_1(int n, long k) {
    int[] ret = new int[n];
    boolean[] check = new boolean[n];

    long sum = 0;
    for (int ptr = 0; ptr < n; ptr++) {
      long f = factorial(n-1-ptr);
      for (int i = 0; i < n; i++) {
        if (check[i]) continue;
        if (sum + f >= k) {
          check[i] = true;
          ret[ptr] = i+1;
          break;
        }
        sum += f;
      }
    }

    return ret;
  }
  static long problem_2(int n, int[] arr) {
    boolean[] check = new boolean[n];
    long sum = 1;
    for (int i = 0; i < n; i++) {
      int num = 0;
      for (int j = 0; j < arr[i]-1; j++) {
        if (check[j]) continue;
        num++;
      }
      sum += factorial(n-1-i)*num;
      check[arr[i]-1] = true;
    }
    return sum;
  }
  static long factorial(int x) {
    if (factorial[x] != 0) return factorial[x];
    if (x <= 1) return factorial[x] = 1;
    return factorial[x] = factorial(x-1) * x;
  }
  static boolean test(int n, int k) {
    return problem_2(n, problem_1(n, k)) == k;
  }
}