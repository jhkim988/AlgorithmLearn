import java.io.*;
import java.util.*;

public class BOJ10531 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int[] shoot = new int[N];
    int max = 0;
    for (int i = 0; i < N; i++) {
      int input = Integer.parseInt(br.readLine()); 
      shoot[i] = input;
      if (max < input) max = input;
    }
    int M = Integer.parseInt(br.readLine());
    int[] dist = new int[M];
    for (int i = 0; i < M; i++) dist[i] = Integer.parseInt(br.readLine());

    int[] poly = new int[max + 1];
    for (int i = 0; i < N; i++) poly[shoot[i]] = 1;

    int[] product = polynomialProduct(poly, poly);
  }

  static int[] polynomialProduct(int[] a, int[] b) {
    int[] fa = fft(a);
    int[] fb = fft(b);
    int[] pw = pointwiseProduct(fa, fb);
  }
}