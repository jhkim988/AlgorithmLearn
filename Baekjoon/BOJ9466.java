import java.io.*;
import java.util.*;

public class BOJ9466 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int numStudent = Integer.parseInt(br.readLine());
      int[] data = new int[numStudent + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < numStudent; i++) {
        data[i + 1] = Integer.parseInt(st.nextToken());
      }
      int count = numOutsider(data, numStudent);
      bw.write(count + "\n");
    }
    bw.flush();
  }
  static int numOutsider(int[] data, int numStudent) {
    int[] marked = new int[numStudent + 1];
    int count = 0;
    for (int i = 1; i <= numStudent; i++) {
      if (marked[i] > 0) continue;
      // System.out.println("start: " + i);
      count += dfs(i, data, marked);
    }
    return count;
  }
  static int dfs(int node, int[] data, int[] marked) {
    int cursor = node;
    int count = 0;
    while (marked[cursor] == 0) {
      // System.out.println("cursor: " + cursor);
      marked[cursor] = node; // marking
      cursor = data[cursor]; // move
      count++;
      if (marked[cursor] > 0) {
        if (marked[cursor] == node) {
          // cycle success
          int cycleLen = 1;
          int start = cursor;
          while (data[cursor] != start) {
            cycleLen++;
            cursor = data[cursor];
          }
          // System.out.println("count: " + count + ", cycleLen: " + cycleLen);
          return count - cycleLen;
        } else {
          // cycle fail
        }
      }
    } 
    // System.out.println("count: " + count);
    return count;
  }
}
