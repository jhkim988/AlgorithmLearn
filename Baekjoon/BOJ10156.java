import java.io.*;
import java.util.*;

public class BOJ10156 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int k = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int answer = k*n-m;
    bw.write(Integer.toString(answer <= 0 ? 0 : answer));
    bw.flush();
  }
}
