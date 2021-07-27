import java.io.*;
import java.util.*;

public class BOJ9375 {
  static int prev = 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  
    String[] tmp;
    HashMap<String, Integer> hm;
    int[][] table = new int[100][100];
    table[1][0] = 1;
    table[1][1] = 1;
    int numTest = Integer.parseInt(br.readLine());
    for (int i = 0; i < numTest; i++) {
      hm = new HashMap<>();
      int numClothes = Integer.parseInt(br.readLine());
      for (int j = 0; j < numClothes; j++) {
        tmp = br.readLine().split(" ");
        if (hm.containsKey(tmp[1])) {
          hm.put(tmp[1], hm.get(tmp[1]).intValue() + 1);
        } else {
          hm.put(tmp[1], 1);
        }
      }
      int product = 1;
      for (int val : hm.values()) {
        product *= val + 1;
      }
      bw.write(product - 1 + "\n");
    }
    bw.flush();
  } 
}
