import java.io.*;
import java.util.*;

public class BOJ3649 {
  static int l1, l2;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    while (input != null) {
      int key = Integer.parseInt(input);
      int n = Integer.parseInt(br.readLine());
      ArrayList<Long> arr = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        arr.add(Long.parseLong(br.readLine()));
      }
      if (find(arr, key*10_000_000)) {
        bw.write("yes ");
        bw.write(Long.toString(arr.get(l1)));
        bw.write(' ');
        bw.write(Long.toString(arr.get(l2)));
        bw.newLine();
      } else {
        bw.write("danger");
        bw.newLine();
      }
      input = br.readLine();
    }
    bw.flush();
  }
  static boolean find(ArrayList<Long> arr, long key) {
    Collections.sort(arr);
    l1 = 0; l2 = arr.size()-1;
    while (l1 < l2) {
      long sum = arr.get(l1) + arr.get(l2);
      if (sum < key) {
        l1++;
      } else if (sum > key) {
        l2--;
      } else {
        return true;
      }
    }
    return false;
  }
}
