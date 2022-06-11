import java.io.*;
import java.util.*;

public class BOJ2420 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long val = Long.parseLong(st.nextToken());
    val -= Long.parseLong(st.nextToken());
    if (val < 0) val = -val;
    bw.write(Long.toString(val));
    bw.flush();
  }
}
