import java.io.*;
import java.util.*;

public class BOJ5639 {
  static ArrayList<Integer> preorder;
  static Stack<Integer> stk;
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    preorder = new ArrayList<>();
    while(sc.hasNextLine()) {
      preorder.add(Integer.parseInt(sc.nextLine()));
    }
    sc.close();
    stk = new Stack<>();
    recur(0, preorder.size() - 1);
    while (!stk.isEmpty()) {
      bw.write(stk.pop() + "\n");
    }
    bw.flush();
  }
  static void recur(int start, int end) {
    if (end < start) {
      return;
    }
    int root = preorder.get(start);
    stk.add(root);
    int idx = start + 1;
    for (idx = start + 1; idx <= end; idx++) {
      if (preorder.get(idx) > root) {
        break;
      }
    }
    recur(idx, end);
    recur(start + 1, idx - 1);
  }
}
