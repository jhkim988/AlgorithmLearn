import java.io.*;

public class BOJ12852 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int N = Integer.parseInt(br.readLine());

      if (N == 1) {
        bw.write(0 + "\n");
        bw.write(1 + "\n");
        bw.flush();
        return;
      }

      int[] table = new int[N + 1];
      int[] path = new int[N + 1];
      table[1] = 0;
      path[1] = 1;
      for (int i = 2; i < table.length; i++) {
        table[i] = table[i - 1] + 1;
        path[i] = i - 1;
        if (i % 3 == 0 && table[i] > table[i/3] + 1) {
          table[i] = table[i/3] + 1;
          path[i] = i/3;
        }
        if (i % 2 == 0 && table[i] > table[i/2] + 1) {
          table[i] = table[i/2] + 1;
          path[i] = i/2;
        }
      }
      bw.write(table[N] + "\n");
      int p = N;
      while(true) {
        bw.write(p + " ");
        p = path[p];
        if (p == 1) {
          bw.write(1 + "\n");
          break;
        }
      }
      bw.flush();    
    } catch (IOException e) {
      // do nothing
    }
  }
}
