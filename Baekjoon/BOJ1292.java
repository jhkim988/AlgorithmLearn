import java.io.*;
import java.util.*;

public class BOJ1292 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int[] arr = new int[1_001];
    
    int count = 1;
    int ptr = 1;
    while (ptr < arr.length) {
      for (int i = 0; i < count; i++) {
        arr[ptr++] = count;
        if (ptr >= arr.length) break;
      }
      count++;
    }
    int sum = 0;
    for (int i = a; i <= b; i++) sum += arr[i];
    bw.write(Integer.toString(sum));
    bw.newLine();
    bw.flush();
  }
}
