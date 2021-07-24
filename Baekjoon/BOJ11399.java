import java.io.*;
import java.util.Arrays;

public class BOJ11399 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int num = Integer.parseInt(br.readLine());
      String[] tmp = br.readLine().split(" ");
      int[] data = new int[num];
      for (int i = 0; i < num; i++) {
        data[i] = Integer.parseInt(tmp[i]);
      }

      Arrays.sort(data);

      for (int i = 1; i < num; i++) {
        data[i] += data[i - 1];
      }
      int sum = 0;
      for (int i = 0; i < num; i++) {
        sum += data[i];
      }

      bw.write(sum + "\n");
      bw.flush();
    } catch (IOException e) {

    }
  }
}
