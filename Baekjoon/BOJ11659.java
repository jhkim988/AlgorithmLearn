import java.io.*;

public class BOJ11659 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] tmp = br.readLine().split(" ");
      int N = Integer.parseInt(tmp[0]);
      int M = Integer.parseInt(tmp[1]);

      tmp = br.readLine().split(" ");
      long[] data = new long[N];
      data[0] = Long.parseLong(tmp[0]);
      for (int i = 1; i < N; i++) {
        data[i] = data[i - 1] + Long.parseLong(tmp[i]);
      }
      
      int first = 0;
      int second = 0;
      for (int i = 0; i < M; i++) {
        tmp = br.readLine().split(" ");
        first = Integer.parseInt(tmp[0]);
        second = Integer.parseInt(tmp[1]);
        if (first == 1) {
          bw.write(data[second - 1] + "\n");
        } else {
          bw.write((data[second - 1] - data[first - 2]) + "\n");
        }        
      }
      
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
