import java.io.*;
import java.util.*;

public class BOJ2693 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      ArrayList<Integer> arr = new ArrayList<>();
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 10; i++) {
        arr.add(Integer.parseInt(st.nextToken()));
      }
      Collections.sort(arr, (a, b) -> b-a);
      bw.write(Integer.toString(arr.get(2)));
      bw.newLine();
    }
    bw.flush();
  }
}
