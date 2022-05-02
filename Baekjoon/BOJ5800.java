import java.io.*;
import java.util.*;

public class BOJ5800 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    for (int i = 1; i <= t; i++) {
      bw.write("Class ");
      bw.write(Integer.toString(i));
      bw.newLine();
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int[] arr = new int[n];
      for (int j = 0; j < n; j++) {
        arr[j] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);
      int gap = 0;
      for (int j = 1; j < n; j++) {
        if (gap < arr[j]-arr[j-1]) gap = arr[j]-arr[j-1];
      }
      bw.write("Max ");
      bw.write(Integer.toString(arr[n-1]));
      bw.write(", Min ");
      bw.write(Integer.toString(arr[0]));
      bw.write(", Largest gap ");
      bw.write(Integer.toString(gap));
      bw.newLine();
    }
    bw.flush();
  }
}
