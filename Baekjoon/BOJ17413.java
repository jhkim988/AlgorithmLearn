import java.io.*;
import java.util.*;

public class BOJ17413 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String input = br.readLine();
    int len = input.length();

    StringBuilder sb = new StringBuilder();
    Stack<Character> stk = new Stack<>();
    int ptr = 0;
    while (ptr < len) {
      char ch = input.charAt(ptr);
      if (ch == '<') { // start of tag
        sb.append('<');
        ptr++;
        while (ptr < len && (ch = input.charAt(ptr)) != '>') {
          sb.append(ch);
          ptr++;
        }
        sb.append('>');
        ptr++;
      } else { // start of word
        stk.push(ch);
        ptr++;
        while (ptr < len && (ch = input.charAt(ptr)) != '<' && ch != ' ') {
          stk.push(ch);
          ptr++;
        }        
        while (!stk.isEmpty()) {
          sb.append(stk.pop());
        }

        if (ch == ' ') {
          sb.append(' ');
          ptr++;
        }
      }
    }

    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }
}