import java.io.*;
import java.util.*;

public class BOJ2346 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int ptr = 0;
    int remainder = n;
    while (remainder-- > 0) {
      bw.write(Integer.toString(ptr+1));
      bw.write(' ');
      int pop = arr[ptr];
      arr[ptr] = 0;
      if (remainder == 0) break;
      while (pop > 0) {
        ptr = (ptr + 1) % n;
        if (arr[ptr] != 0) pop--;
      }
      while (pop < 0) {
        ptr = (ptr + n - 1) % n;
        if (arr[ptr] != 0) pop++;
      }
    }
    bw.newLine();
    bw.flush();
  }
}
