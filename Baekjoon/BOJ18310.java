import java.io.*;
import java.util.*;

public class BOJ18310 {
  // Consider Sweeping
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    if (n <= 2) {
      bw.write(Integer.toString(arr[0]));
    } else if (n % 2 == 1) {
      Arrays.sort(arr);
      bw.write(Integer.toString(arr[arr.length/2]));
    } else {
      Arrays.sort(arr);
      int mid = (arr.length-1)/2;
      int dist = arr[mid+1]-arr[mid];
      if (mid*dist - (arr.length-mid-2)*dist < 0) bw.write(Integer.toString(arr[mid+1]));
      else bw.write(Integer.toString(arr[mid]));
    }
    bw.flush();
  }
}
