import java.io.*;
import java.util.*;

public class BOJ12015 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int len = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] table = new int[len + 1];
    int ptr = 1;
    for (int i = 0; i < len; i++) {
      int input = Integer.parseInt(st.nextToken());
      if (table[ptr - 1] < input) {
        table[ptr++] = input;
      } else {
        int idx = Arrays.binarySearch(table, 0, ptr, input); // [0, ptr)
        if (idx < 0) {
          idx = -(idx + 1);
        }
        table[idx] = input;
      }
    }
    bw.write(ptr - 1 + "\n");
    bw.flush();    
  }
}
