import java.io.*;

public class BOJ10989 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      int numData = Integer.parseInt(br.readLine());
      int[] data = new int[numData];
      int max = 0;
      for (int i = 0; i < numData; i++) {
        data[i] = Integer.parseInt(br.readLine());
        max = data[i] > max ? data[i] : max;
      }
      br.close();

      int[] tmp = new int[max]; // tmp[i - 1]: count of i
      for (int i = 0; i < numData; i++) {
        tmp[data[i] - 1]++;
      }

      for (int i = 0; i < max; i++) {
        for (int j = 0; j < tmp[i]; j++) {
          bw.write((i + 1) + "\n");
        }
      }

      bw.flush();
      bw.close();
    } catch (IOException e) {
      // do nothing
    }
  }
}
