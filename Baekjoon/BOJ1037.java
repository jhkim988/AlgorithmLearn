import java.io.*;
import java.util.*;

public class BOJ1037 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int num = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    int[] data = new int[num];
    for (int i = 0; i < num; i++) {
      data[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(data);
    bw.write(data[0] * data[num - 1] + "\n");
    bw.flush();
  }
}
