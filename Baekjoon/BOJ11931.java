import java.io.*;
import java.util.*;

public class BOJ11931 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Integer> arr = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      arr.add(Integer.parseInt(br.readLine()));
    }
    Collections.sort(arr, Collections.reverseOrder());
    for (int val : arr) {
      bw.write(Integer.toString(val));
      bw.newLine();
    }
    bw.flush();
  }
}
