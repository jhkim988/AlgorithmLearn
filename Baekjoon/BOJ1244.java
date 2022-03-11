import java.io.*;
import java.util.*;

public class BOJ1244 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    int n = Integer.parseInt(br.readLine());
    boolean[] arr = new boolean[n+1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken()) == 1;
    }
    int m = Integer.parseInt(br.readLine());
    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int num = Integer.parseInt(st.nextToken());
      action(type, num, arr);
    }
    print(arr);
    bw.flush();
  }
  static void action(int type, int num, boolean[] arr) {
    if (type == 1) {
      for (int i = num; i < arr.length; i += num) {
        arr[i] = !arr[i];
      }
    } else {
      arr[num] = !arr[num];
      for (int i = 1; i < arr.length; i++) {
        if (num + i >= arr.length || num - i < 1) break;
        if (arr[num + i] == arr[num - i]) {
          arr[num - i] = !arr[num - i];
          arr[num + i] = !arr[num + i];
        } else {
          break;
        }
      }
    }
  }
  static void print(boolean[] arr) throws IOException {
    int num = 0;
    for (int i = 1; i < arr.length; i++) {
      bw.write(arr[i] ? '1' : '0');
      bw.write(' ');
      num++;
      if (num == 20) {
        bw.newLine();
        num = 0;
      }
    }
  }
}
