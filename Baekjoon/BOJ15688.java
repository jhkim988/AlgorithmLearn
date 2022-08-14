import java.io.*;

public class BOJ15688 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] count = new int[2_000_001];

    int min = 1_000_000, max = -1_000_000;
    for (int i = 0; i < n; i++) {
      int input = Integer.parseInt(br.readLine());
      if (input < min) min = input;
      if (input > max) max = input;
      count[input+1_000_000]++;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = min+1_000_000; i <= max+1_000_000; i++) {
      for (int x = 0; x < count[i]; x++) {
        sb.append(Integer.toString(i-1_000_000));
        sb.append('\n');
      }
    }
    bw.write(sb.toString());
    bw.flush();
  }
}
