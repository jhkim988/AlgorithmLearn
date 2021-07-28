import java.io.*;
import java.util.*;

public class BOJ5430 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());
    for (int i = 0; i < numTest; i++) {
      bw.write(test() + "\n");
    }
    bw.flush();
  }
  static String test() throws IOException {
    String command = br.readLine();
    int numData = Integer.parseInt(br.readLine());
    Deque<Integer> deq = new LinkedList<>();
    StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
    for (int i = 0; i < numData; i++) {
      deq.addLast(Integer.parseInt(st.nextToken()));
    }
    
    int len = command.length();
    boolean flag = true; // reverse: false
    for (int i = 0; i < len; i++) {
      if (command.charAt(i) == 'R') {
        flag = !flag;
      } else {
        if (deq.isEmpty()) {
          return "error";
        }
        if (flag) {
          deq.removeFirst();
        } else {
          deq.removeLast();
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    Iterator<Integer> iter = flag ? deq.iterator() : deq.descendingIterator();
    sb.append("[");
    while (iter.hasNext()) {
      sb.append(iter.next() + ",");
    }
    if (sb.charAt(sb.length() - 1) != '[') {
      sb.replace(sb.length() - 1, sb.length(), "");
    }
    sb.append("]");
    return sb.toString();
  }
}
