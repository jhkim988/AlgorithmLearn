import java.io.*;
import java.util.*;

public class BOJ2696 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      PriorityQueue<Integer> lo = new PriorityQueue<>((a, b) -> b-a);
      PriorityQueue<Integer> hi = new PriorityQueue<>();
      lo.add(Integer.parseInt(st.nextToken()));
      bw.write(Integer.toString((n+1)/2));
      bw.newLine();
      bw.write(Integer.toString(lo.peek()));
      bw.write(' ');
      int num = 1;
      int numPrint = 1;
      while (--n > 0) {
        if (num % 10 == 0) st = new StringTokenizer(br.readLine());
        num++;
        int input = Integer.parseInt(st.nextToken());
        if (lo.peek() < input) {
          hi.add(input);
        } else {
          lo.add(input);
          hi.add(lo.poll());
        }
        while (lo.size() < hi.size()) {
          lo.add(hi.poll());
        }
        if (num % 2 == 1) {
          if (numPrint % 10 == 0) bw.newLine();
          bw.write(Integer.toString(lo.peek()));
          bw.write(' ');
          numPrint++;
        }
      }
      bw.newLine();
    }
    bw.flush();
  }  
}
