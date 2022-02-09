import java.io.*;
import java.util.*;

public class BOJ2217 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] weight = new int[n];
    for (int i = 0; i < n; i++) {
      weight[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(weight);
    int max = 0;
    for (int i = n-1; i >= 0; i--) {
      if (max < weight[i] * (n-i)) max = weight[i] * (n-i);
    }
    bw.write(Integer.toString(max));
    bw.newLine();
    bw.flush();
  }
}
