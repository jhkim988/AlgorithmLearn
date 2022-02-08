import java.io.*;
import java.util.*;

public class BOJ1026 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] A = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    int[] B = new int[n];
    for (int i = 0; i < n; i++) {
      B[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(A);
    Arrays.sort(B);
    int val = 0;
    for (int i = 0; i < n; i++) {
      val += A[i]*B[n-i-1];
    }
    bw.write(Integer.toString(val));
    bw.newLine();
    bw.flush();
  }
}
