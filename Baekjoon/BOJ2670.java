import java.io.*;

public class BOJ2670 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    double[] pp = new double[n];
    for (int i = 0; i < n; i++) {
      pp[i] = Double.parseDouble(br.readLine());
    }
    double max = Double.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      double val = 1;
      for (int j = 0; i+j < n; j++) {
        val *= pp[i+j];
        max = Double.max(max, val);
      }
    }
    System.out.printf("%.3f", max);
  }  
}
