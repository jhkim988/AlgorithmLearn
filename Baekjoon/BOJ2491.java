import java.io.*;
import java.util.*;
public class BOJ2491 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    if (n == 1) {
      bw.write("1\n");
      bw.flush();
      return;
    }
    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int[] diff = new int[n-1];
    for (int i = 0; i < n-1; i++) {
      int input = Integer.parseInt(st.nextToken());
      diff[i] = input - a;
      a = input;
    }
    int answer = 1;
    int posi = 0, nega = 0, zero = 0; // positive, negative, zero
    if (diff[0] > 0) posi = 2;
    else if (diff[0] < 0) nega = 2;
    else zero = 2;
    for (int i = 1; i < n-1; i++) {
      System.out.println("diff[i]: " + diff[i]);
      if (posi > 0) {
        if (diff[i] > 0) {
          posi = posi + zero + 1;
          answer = Integer.max(answer, posi);
        } else if (diff[i] < 0) {
          posi = 0;
          nega = 2;
          zero = 0;
          answer = Integer.max(answer, nega);
        } else {
          zero++;
        }
      } else { // nega > 0
        if (diff[i] < 0) {
          nega++;
          answer = Integer.max(answer, nega);
        } else if (diff[i] > 0) {
          posi = 2;
          nega = 0;
          zero = 0;
          answer = Integer.max(answer, posi);
        }
      } 
    }
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
}
