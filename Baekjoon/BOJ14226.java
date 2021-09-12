import java.io.*;
import java.util.*;

public class BOJ14226 {
  private static class Node {
    int numEmo;
    int clipBoard;
    int count;
    Node(int numEmo, int clipBoard, int count) {
      this.numEmo = numEmo;
      this.clipBoard = clipBoard;
      this.count = count;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int len = 1_002;
    int S = Integer.parseInt(br.readLine());
    
    Queue<Node> que = new LinkedList<>();
    boolean[][] marked = new boolean[len][len];
    Node start = new Node(1, 0, 0);
    que.add(start);

    while (!que.isEmpty()) {
      Node crnt = que.poll();
      marked[crnt.numEmo][crnt.clipBoard] = true;
      if (crnt.numEmo == S) {
        bw.write(crnt.count + "\n");
        bw.flush();
        break;
      }      
      if (!marked[crnt.numEmo][crnt.numEmo]) {
        Node copy = new Node(crnt.numEmo, crnt.numEmo, crnt.count + 1);
        que.add(copy);
      }
      if (crnt.clipBoard != 0 && crnt.numEmo + crnt.clipBoard < len && !marked[crnt.numEmo + crnt.clipBoard][crnt.clipBoard]) {
        Node paste = new Node(crnt.numEmo + crnt.clipBoard, crnt.clipBoard, crnt.count + 1);
        que.add(paste);
      }
      if (crnt.numEmo > 0 && !marked[crnt.numEmo - 1][crnt.clipBoard]) {
        Node oneDel = new Node(crnt.numEmo - 1, crnt.clipBoard, crnt.count + 1);
        que.add(oneDel);
      }
    }
  }
}
