import java.io.*;
import java.util.*;

public class BOJ1406 {
  static BufferedReader br;
  static BufferedWriter bw;
  private static class llist {
    char ch;
    llist next;
    llist prev;
    llist(char ch) {
      this.ch = ch;
    }
  }
  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    char[] init = br.readLine().toCharArray();
    int numCommand = Integer.parseInt(br.readLine());

    // bw.write(useLinkedList(init, numCommand));
    bw.write(useTwoStack(init, numCommand));
    bw.flush();
  }
  static String useLinkedList(char[] init, int numCommand) throws IOException {
    llist head = new llist(init[0]);
    llist ptr = head;
    for (int i = 1; i < init.length; i++) {
      ptr.next = new llist(init[i]);
      ptr.next.prev = ptr;
      ptr = ptr.next;      
    }
    
    llist tail = new llist('0');
    tail.prev = ptr;
    ptr.next = tail;
    ptr = tail;

    while (numCommand-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      char oper = st.nextToken().charAt(0);
      switch (oper) {
        case 'L': {
          if (ptr.prev != null) {
            ptr = ptr.prev;
          }
          break;
        }
        case 'D': {
          if (ptr.next != null) {
            ptr = ptr.next;
          }
          break;
        }
        case 'B': {
          if (ptr.prev == null) {
            // do nothing
          } else if (ptr.prev.prev == null) {
            head = ptr;
            ptr.prev = null;
          } else {
            ptr.prev.prev.next = ptr;
            ptr.prev = ptr.prev.prev;
          }
          break;
        }
        case 'P': {
          char ch = st.nextToken().charAt(0);
          llist insert = new llist(ch);
          if (ptr.prev == null) {
            head = insert;
            head.next = ptr;
            ptr.prev = head;
          } else {
            insert.prev = ptr.prev;
            insert.prev.next = insert;
            insert.next = ptr;
            ptr.prev = insert;
          }
          break;
        }
      }
    }

    StringBuilder sb = new StringBuilder();

    llist iter = head;
    while (iter != tail) {
      sb.append(iter.ch);
      iter = iter.next;
    }
    sb.append('\n');
    return sb.toString();
  }
  static String useTwoStack(char[] init, int numCommand) throws IOException {
    Stack<Character> left = new Stack<>();
    Stack<Character> right = new Stack<>();

    for (int i = 0; i < init.length; i++) {
      left.push(init[i]);
    }

    while (numCommand-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      char oper = st.nextToken().charAt(0);
      switch (oper) {
        case 'L': {
          if (!left.isEmpty()) {
            right.push(left.pop());
          }
          break;
        }
        case 'D': {
          if (!right.isEmpty()) {
            left.push(right.pop());
          }
          break;
        }
        case 'B': {
          if (!left.isEmpty()) {
            left.pop();
          }
          break;
        }
        case 'P': {
          char ch = st.nextToken().charAt(0);
          left.push(ch);
          break;
        }
      }
    }
    Stack<Character> tmp = new Stack<>();
    while (!left.isEmpty()) {
      tmp.push(left.pop());
    }
    StringBuilder sb = new StringBuilder();
    while (!tmp.isEmpty()) {
      sb.append(tmp.pop());
    }
    while (!right.isEmpty()) {
      sb.append(right.pop());
    }
    sb.append('\n');
    return sb.toString();
  }
}