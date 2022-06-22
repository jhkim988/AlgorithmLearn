import java.io.*;
import java.util.*;

public class BOJ11508 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr);
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      if ((i+1) % 3 == 0) continue;
      sum += arr[arr.length-1-i];
    }
    bw.write(Integer.toString(sum));
    bw.flush();
  }
}
