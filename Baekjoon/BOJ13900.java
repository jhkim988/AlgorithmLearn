import java.io.*;
import java.util.*;

public class BOJ13900 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    long sum = 0;
    long sqsum = 0;
    while (n-- > 0) {
      int input = Integer.parseInt(st.nextToken());
      sum += input;
      sqsum += input*input;
    }
  
    bw.write(Long.toString((sum*sum-sqsum)/2));
    bw.flush();
  }
}