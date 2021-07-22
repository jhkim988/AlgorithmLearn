import java.io.*;

public class BOJ2579_sol2 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numStep = Integer.parseInt(br.readLine());
      int[] data = new int[numStep];
      int[] table = new int[numStep];
      int sumData = 0;
      for (int i = 0; i < numStep; i++) {
        data[i] = Integer.parseInt(br.readLine());
        sumData += data[i];
      }
      
      if (numStep <= 2) {
        bw.write(sumData + "\n");
        bw.flush();
        return;
      }

      table[0] = data[0];
      table[1] = data[1];
      table[2] = data[2];

      for (int i = 3; i < numStep; i++) {
        table[i] = data[i] + Math.min(table[i - 2], table[i - 3]);
      }

      bw.write(sumData - Math.min(table[numStep - 2], table[numStep - 3]) + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
