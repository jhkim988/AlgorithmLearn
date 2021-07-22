import java.io.*;

public class BOJ11054 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numData = Integer.parseInt(br.readLine());
      int[] data = new int[numData];
      for (int i = 0; i < numData; i++) {
        data[i] = Integer.parseInt(br.readLine());
      }

      

    } catch (IOException e) {
      // do nothing
    }
  }
}
