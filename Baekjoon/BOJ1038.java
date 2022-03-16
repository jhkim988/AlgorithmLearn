import java.io.*;
import java.util.*;

public class BOJ1038 {
  static ArrayList<Long> al = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    for (int i = 0; i <= 9; i++) {
      recur(i);
      al.add((long) i);
    }
    Collections.sort(al);
    if (al.size() <= n) {
      bw.write("-1\n");
    } else {
      bw.write(Long.toString(al.get(n)));
      bw.newLine();
    }
    bw.flush();
  }
  static void recur(long prev) {
    long digit = 1;
    while (prev / (digit * 10) > 0) {
      digit *= 10;
    }
    for (long i = prev/digit+1; i <= 9; i++) {
      al.add(prev + i*digit*10);
      recur(prev + i*digit*10);
    }
  }
}