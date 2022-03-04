import java.io.*;

public class BOJ2631 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    int ptr = 1;
    int[] dp = new int[n+1];
    dp[1] = arr[0];
    for (int i = 1; i < n; i++) {
      int idx = find(arr[i], dp, 0, ptr+1);
      dp[idx] = arr[i];
      if (ptr+1 == idx) ptr++; 
    }
    bw.write(Integer.toString(n - ptr));
    bw.newLine();
    bw.flush();
  }
  static int find(int key, int[] arr, int start, int end) {
    while (start + 1 < end) {
      int mid = (start + end) >> 1;
      if (arr[mid] < key) {
        start = mid;
      } else {
        end = mid;
      }
    }
    return end;
  }
}
