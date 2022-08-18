import java.io.*;
import java.util.*;

public class BOJ2485 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(arr);
    int gcd = arr[1]-arr[0];
    for (int i = 1; i < arr.length; i++) {
      gcd = gcd(gcd, arr[i]-arr[i-1]);
    }
    bw.write(Integer.toString((arr[arr.length-1]-arr[0])/gcd - n + 1));
    bw.flush();
  }
  static int gcd(int a, int b) {
    int r = a%b;
    while (r > 0) {
      a = b;
      b = r;
      r = a%b;
    }
    return b;
  }
}