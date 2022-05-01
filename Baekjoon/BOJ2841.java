import java.io.*;
import java.util.*;

public class BOJ2841 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    Integer.parseInt(st.nextToken());
    ArrayList<Stack<Integer>> stks = new ArrayList<>();
    for (int i = 0; i <= 6; i++) {
      stks.add(new Stack<>());
    }

    int move = 0;
    while (n-- > 0) {
      st = new StringTokenizer(br.readLine());
      int str = Integer.parseInt(st.nextToken());
      int fret = Integer.parseInt(st.nextToken());
      Stack<Integer> s = stks.get(str);
      while (!s.isEmpty() && fret < s.peek()) {
        s.pop();
        move++;
      }
      if (!s.isEmpty() && s.peek() == fret) continue;
      s.push(fret);
      move++;
    }
    bw.write(Integer.toString(move));
    bw.flush();
  }
}
