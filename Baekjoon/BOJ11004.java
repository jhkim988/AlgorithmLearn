import java.io.*;
import java.util.*;

public class BOJ11004 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    int[] input = new int[n];
    for (int i = 0; i < n; i++) input[i] = Integer.parseInt(st.nextToken());
    Arrays.sort(input);
    bw.write(Integer.toString(input[k-1]));
    bw.newLine();
    bw.flush();
  }
}
