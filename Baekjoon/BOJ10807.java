import java.io.*;
import java.util.*;

public class BOJ10807 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int key = Integer.parseInt(br.readLine());
    int num = 0;
    for (int i = 0; i < n; i++) {
      if (key == arr[i]) num++;
    }

    bw.write(Integer.toString(num));
    bw.flush();
  }
}
