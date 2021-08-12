import java.io.*;
import java.util.*; 
// int Overflow
public class BOJ16953 {
  private static class Pair {
    long num;
    int count;
    Pair(long num, int count) {
      this.num = num;
      this.count = count;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    long A = Long.parseLong(st.nextToken());
    long B = Long.parseLong(st.nextToken());

    Queue<Pair> que = new LinkedList<>();
    HashSet<Long> marked = new HashSet<>();
    marked.add(A);
    que.add(new Pair(A, 1));
    Pair crnt = new Pair(A, -1); // dummy
    boolean flag = false;
    while (!que.isEmpty()) {
      crnt = que.poll();
      if (crnt.num == B) {
        flag = true;
        break;
      }
      if (!marked.contains(2 * crnt.num) && 2 * crnt.num <= B) {
        que.add(new Pair(2 * crnt.num, crnt.count + 1));
      }
      if (!marked.contains(crnt.num * 10 + 1) && crnt.num * 10 + 1 <= B) {
        que.add(new Pair(crnt.num * 10 + 1, crnt.count + 1));
      }
    }
    if (flag) {
      bw.write(crnt.count + "\n");
    } else {
      bw.write("-1\n");
    }
    bw.flush();
  }
}
