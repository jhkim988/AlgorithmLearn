import java.io.*;
import java.util.*;

public class BOJ1041 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long n = Long.parseLong(br.readLine());
    long[] arr = new long[6];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 6; i++) {
      arr[i] = Long.parseLong(st.nextToken());
    }
    if (n == 1) {
      long sum = 0;
      long max = 0;
      for (int i = 0; i < 6; i++) {
        sum += arr[i];
        if (max < arr[i]) max = arr[i];
      }
      bw.write(Long.toString(sum-max));
      bw.flush();
      return;
    }
    long min1 = Long.MAX_VALUE/2;
    long min2 = Long.MAX_VALUE/2;
    long min3 = Long.MAX_VALUE/2;
    for (int i = 0; i < 6; i++) {
      min1 = Long.min(min1, arr[i]);
    }
    for (int i = 0; i < 6; i += 5) {
      for (int j = 1; j <= 4; j++) {
        min2 = Long.min(min2, arr[i]+arr[j]);
      }
    }
    min2 = Long.min(min2, arr[1]+arr[2]);
    min2 = Long.min(min2, arr[1]+arr[3]);
    min2 = Long.min(min2, arr[2]+arr[4]);
    min2 = Long.min(min2, arr[3]+arr[4]);
    for (int i = 0; i < 6; i += 5) {
      min3 = Long.min(min3, arr[i] + arr[1] + arr[2]);
      min3 = Long.min(min3, arr[i] + arr[1] + arr[3]);
      min3 = Long.min(min3, arr[i] + arr[2] + arr[4]);
      min3 = Long.min(min3, arr[i] + arr[3] + arr[4]);
    }
    long num1 = 5*n*n-16*n+12;
    long num2 = 8*n-12;
    long num3 = 4;
    bw.write(Long.toString(num1*min1 + num2*min2 + num3*min3));
    bw.flush();
  }
}
