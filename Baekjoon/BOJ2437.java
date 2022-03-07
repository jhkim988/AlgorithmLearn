import java.io.*;
import java.util.*;

public class BOJ2437 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    int sum = 0;
    for (int i = 0; i < n; i++) {
      if (sum + 1 < arr[i]) {
        break;
      } else {
        sum += arr[i];
      }
    }
    bw.write(Integer.toString(sum+1));
    bw.newLine();
    bw.flush();
  }
}
