import java.io.*;

public class BOJ17427_sol {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    long result = 0L;
    for (int i = 1; i <= N; i++) {
      result += (N/i) * i;
    }
    bw.write(result + "\n");
    bw.flush();
  }  
}
