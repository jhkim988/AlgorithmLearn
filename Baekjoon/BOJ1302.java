import java.io.*;
import java.util.*;

public class BOJ1302 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] max = {0};
    String maxTitle = "";
    HashMap<String, Integer> hm = new HashMap<>();
    while (n-- > 0) {
      String title = br.readLine();
      if (hm.containsKey(title)) {
        int val = hm.get(title);
        hm.put(title, val+1);
        maxTitle = renew(max, val+1, maxTitle, title);
      } else {
        hm.put(title, 1);
        maxTitle = renew(max, 1, maxTitle, title);
      }
    }
    bw.write(maxTitle);
    bw.newLine();
    bw.flush();
  }
  static String renew(int[] max, int val, String maxTitle, String title) {
    if (max[0] < val+1) {
      max[0] = val+1;
      maxTitle = title;
    } else if (max[0] == val+1) {
      if (maxTitle.compareTo(title) > 0) {
        maxTitle = title;
      }
    }
    return maxTitle;
  }
}
