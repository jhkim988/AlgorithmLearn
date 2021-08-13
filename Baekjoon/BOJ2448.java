import java.io.*;

public class BOJ2448 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    StringBuilder sb = sierpinskiPrint(N);
    bw.write(sb.toString());
    bw.flush();
  }
  static StringBuilder sierpinskiPrint(int N) {
    StringBuilder[] arr = sierpinski(N);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      sb.append(arr[i]).append('\n');
    }
    return sb;
  }
  static StringBuilder[] sierpinski(int N) { // N: height
    StringBuilder[] print = new StringBuilder[N];
    if (N == 3) {
      print[0] = new StringBuilder("  *   ");
      print[1] = new StringBuilder(" * *  ");
      print[2] = new StringBuilder("***** ");
      return print;
    }
    StringBuilder[] prev = sierpinski(N/2);
    for (int i = 0; i < N/2; i++) {
      print[i] = space(N/2).append(prev[i]).append(space(N/2));
    }
    for (int j = 0; j < N/2; j++) {
      print[j + N/2] = prev[j].append(prev[j]);
    }
    return print;
  }
  static StringBuilder space(int n) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < n; i++) {
      result.append(' ');
    }
    return result;
  }
}