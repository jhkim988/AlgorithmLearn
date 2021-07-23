import java.io.*;

public class BOJ1912 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int num = Integer.parseInt(br.readLine());
      int[] data = new int[num];

      String[] tmp = br.readLine().split(" ");
      for (int i = 0; i < num; i++) {
        data[i] = Integer.parseInt(tmp[i]);
      }
      tmp = null;

      int[] table = new int[num];
      table[0] = data[0];
      int max = table[0];
      for (int i = 1; i < num; i++) {
        table[i] = Math.max(table[i - 1] + data[i], data[i]);
      }
      
      for (int i = 0; i < num; i++) {
        if (max < table[i]) {
          max = table[i];
        }
      }

      bw.write(max + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
