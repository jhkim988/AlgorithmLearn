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

    bw.write(useLinkedList(init, numCommand));
    // bw.write(useTwoStack(init, numCommand));
    bw.flush();
  }
  static String useLinkedList(char[] init, int numCommand) throws IOException {
    llist head = new llist(init[0]);
    llist ptr = head;
    for (int i = 1; i < init.length; i++) {
      ptr.next = new llist(init[1]);
      ptr.next.prev = ptr;
      ptr = ptr.next;      
    }

    llist cursor = new llist('0');
    ptr.next = cursor;
    cursor.prev = ptr;

    while (numCommand-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      char oper = st.nextToken().charAt(0);
      switch (oper) {
        case 'L': { // swap: cursor and cursor.prev
          if (cursor.prev == null) {
            // do nothing
          } else if (cursor.prev.prev == null) {
            llist copyCursor = new llist('0');
            copyCursor.prev = head;
            copyCursor.next = cursor.next;
            cursor.next = head;
            head.next = copyCursor.next;
            head.next.prev = head;
            head.prev = cursor;
            head = cursor;
            copyCursor = null;
          } else {
            
          }
          break;
        }
        case 'D': {
          llist.swap(cursor, cursor.next);
          break;
        }
        case 'B': {
          if (cursor.prev == null) {
            // do nothing
          } else if (cursor.prev.prev == null) {
            head = cursor;
            cursor.prev = null;
          } else {
            cursor.prev.prev.next = cursor;
            cursor.prev = cursor.prev.prev;
          }
          break;
        }
        case 'P': {
          char ch = st.nextToken().charAt(0);
          llist insert = new llist(ch);
          if (cursor.prev == null) {
            head = insert;
            head.next = cursor;
            cursor.prev = head;
          } else {
            insert.prev = cursor.prev;
            insert.prev.next = insert;
            insert.next = cursor;
            cursor.prev = insert;
          }
          break;
        }
      }
    }
  }
}