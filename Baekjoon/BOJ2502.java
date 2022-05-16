import java.io.*;
import java.util.*;

public class BOJ2502 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int d = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[] a1 = new int[d+1];
    int[] a2 = new int[d+1];
    a1[1] = 1; a2[2] = 1;
    for (int i = 3; i <= d; i++) {
      a1[i] = a1[i-1]+a1[i-2];
      a2[i] = a2[i-1]+a2[i-2];
    }
    for (int i = 1; i <= k; i++) {
      if ((k-a1[d]*i)%a2[d] == 0) {
        bw.write(Integer.toString(i));
        bw.newLine();
        bw.write(Integer.toString((k-a1[d]*i)/a2[d]));
        bw.flush();
        return;
      }
    }
  }
}