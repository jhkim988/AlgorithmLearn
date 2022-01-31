import java.io.*;
import java.util.*;

public class BOJ1016 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long min = Long.parseLong(st.nextToken());
    long max = Long.parseLong(st.nextToken());
    int len = (int) (max - min + 1);
    boolean[] table = new boolean[len];
    int count = len;
    for (long val = 2; val * val <= max; val++) {
      long sq = val * val;
      long cursor = min / sq + (min % sq == 0 ? 0 : 1);
      cursor *= sq;
      if (cursor - min >= len) continue;
      for (long ptr = (cursor - min); ptr < len; ptr += sq) {
        if (!table[(int) ptr]) count--;
        table[(int) ptr] = true;
      }
    }
  
    bw.write(Integer.toString(count));
    bw.newLine();
    bw.flush();
  }
}