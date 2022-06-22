import java.io.*;
import java.util.*;

public class BOJ1758 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Integer> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      arr.add(Integer.parseInt(br.readLine()));
    }
    Collections.sort(arr, Collections.reverseOrder());
    long sum = 0;
    for (int i = 0; i < n; i++) {
      if (arr.get(i) - i <= 0) break;
      sum = sum + arr.get(i) - i;
    }
    bw.write(Long.toString(sum));
    bw.flush();
  }
}
