import java.io.*;
import java.util.*;

public class BOJ18917 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int q = Integer.parseInt(br.readLine());
    long sum = 0;
    int xor = 0;
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      switch (type) {
        case 1: {
          int input = Integer.parseInt(st.nextToken());
          sum += input;
          xor ^= input;
          break;
        } case 2: {
          int input = Integer.parseInt(st.nextToken());
          sum -= input;
          xor ^= input;
          break;
        } case 3: {
          bw.write(Long.toString(sum));
          bw.newLine();
          break;
        } case 4: {
          bw.write(Integer.toString(xor));
          bw.newLine();
          break;
        }
      }
    }
    bw.flush();
  }
}
