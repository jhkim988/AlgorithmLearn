import java.io.*;
import java.util.*;

public class BOJ1755 {
  private static class Pair {
    int val;
    String word;
    Pair(int val, String word) {
      this.val = val;
      this.word = word;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());


    String[] dict = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    ArrayList<Pair> arr = new ArrayList<>();
    for (int i = 0; i <= n-m; i++) {
      arr.add(new Pair(i+m, (i+m) >= 10 ? (dict[(i+m)/10] + " " + dict[(i+m)%10]) : dict[(i+m)%10]));
    }
    Collections.sort(arr, (a, b) -> a.word.compareTo(b.word));

    int num = 0;
    for (Pair p : arr) {
      bw.write(Integer.toString(p.val));
      bw.write(' ');
      num++;
      if (num == 10) {
        bw.newLine();
        num = 0;
      }
    }
    bw.flush();
  }
}