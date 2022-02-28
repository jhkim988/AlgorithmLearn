import java.io.*;

public class BOJ5397 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private static class Node {
    Node prev, next;
    char ch;
  }
  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      char[] input = br.readLine().toCharArray();
      Node cursor = new Node();
      for (int i = 0; i < input.length; i++) {
        if (input[i] == '<') {
          if (cursor.prev != null) cursor = cursor.prev;
        } else if (input[i] == '>') {
          if (cursor.next != null) cursor = cursor.next;
        } else if (input[i] == '-') {
          if (cursor.next != null && cursor.prev != null) {
            Node prev = cursor.prev;
            Node next = cursor.next;
            prev.next = next;
            next.prev = prev;
            cursor = prev;
          }
          if (cursor.prev != null && cursor.next == null) {
            cursor = cursor.prev;
            cursor.next = null;
          }
        } else {
          Node newNode = new Node();
          newNode.ch = input[i];
          newNode.prev = cursor;
          newNode.next = cursor.next;
          if (cursor.next != null) cursor.next.prev = newNode;
          cursor.next = newNode;
          cursor = newNode;
        }
      }
      print(cursor);
    }
    bw.flush();
  }
  static void print(Node node) throws IOException {
    Node start = node;
    while (start.prev != null) start = start.prev;
    while (start != null) {
      if (start.ch != 0) {
        bw.write(start.ch);
      }
      start = start.next;
    }
    bw.newLine();
  }
}
