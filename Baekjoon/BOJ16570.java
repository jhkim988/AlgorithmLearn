import java.io.*;
import java.util.*;

public class BOJ16570 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = n-1; i >= 0; i--) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int[] pi = pi(arr);
    int[] num = new int[n];
    for (int i = n-1; i > 0; i--) {
      num[pi[i]]++;
    }

    for (int i = n-1; i > 0; i--) {
      if (num[i] == 0) continue;
      bw.write(Integer.toString(i));
      bw.write(' ');
      bw.write(Integer.toString(num[i]));
      bw.flush();
      return;
    }
    bw.write("-1");
    bw.flush();
  }
  static int[] pi(int[] arr) {
    int[] pi = new int[arr.length];
    int j = 0;
    for (int i = 1; i < arr.length; i++) {
      while (j > 0 && arr[i] != arr[j]) j = pi[j-1];
      if (arr[i] == arr[j]) pi[i] = ++j;
    }
    return pi;
  }
}
