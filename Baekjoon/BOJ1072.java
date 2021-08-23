import java.io.*;
import java.util.*;

public class BOJ1072 {
  public static void main(String[] args) throws IOException {
    // binSearch();
    math();
  }
  static void math() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long X = Long.parseLong(st.nextToken());
    long Y = Long.parseLong(st.nextToken());
    long Z = Y * 100 / X;

    if (Z >= 99) {
      bw.write("-1\n");
      bw.flush();
      return;
    }

    long numer = 100 * Y - X * Z - X;
    long deno = Z - 99;
    long T = numer / deno;
    
    while ((Y + T) * 100 / (X + T) == Z) {
      T++;
    }

    bw.write(T + "\n");
    bw.flush();
  }
  static void binSearch() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long X = Long.parseLong(st.nextToken());
    long Y = Long.parseLong(st.nextToken());
    long Z = Y * 100 / X;
    if (Z >= 99) {
      bw.write("-1\n");
      bw.flush();
      return;
    }

    long lower = 0;
    long upper = X + 1;
    while (lower < upper) { // [lower, upper)
      long mid = (lower + upper) / 2;
      if ((Y + mid) * 100 / (X + mid) > Z) {
        upper = mid;
      } else {
        lower = mid + 1;
      }
    }
    bw.write(upper + "\n");
    bw.flush();
  }
}
