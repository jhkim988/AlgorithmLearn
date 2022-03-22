import java.io.*;

public class BOJ2847 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    int sum = 0;
    for (int i = n-2; i >= 0; i--) {
      if (arr[i+1] <= arr[i]) {
        sum += arr[i]-arr[i+1]+1;
        arr[i] = arr[i+1]-1;
      }
    }
    bw.write(Integer.toString(sum));
    bw.newLine();
    bw.flush();
  }
}