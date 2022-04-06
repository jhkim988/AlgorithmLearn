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
    int numZero = 0;
    for (int i = 0; i < n-1; i++) {
      int input = Integer.parseInt(st.nextToken());
      diff[i] = input - a;
      a = input;
      if (diff[i] == 0) numZero++;
    }
    if (numZero == n-1) {
      bw.write(Integer.toString(n));
      bw.newLine();
      bw.flush();
      return;
    }
    int answer = 1;
    boolean[] visit = new boolean[n-1];
    for (int i = 0; i < n-1; i++) {
      if (visit[i]) continue;
      if (diff[i] == 0) continue;
      visit[i] = true;
      int len = 2;
      if (diff[i] > 0) {
        for (int j = 1; i+j < n-1; j++) {
          if (diff[i+j] < 0) break;
          if (diff[i+j] != 0) visit[i+j] = true;
          len++;
        }
        for (int j = 1; i-j >= 0; j++) {
          if (diff[i-j] < 0) break;
          if (diff[i-j] != 0) visit[i-j] = true;
          len++;
        }
      } else {
        for (int j = 1; i+j < n-1; j++) {
          if (diff[i+j] > 0) break;
          if (diff[i+j] != 0) visit[i+j] = true;
          len++;
        }
        for (int j = 1; i-j >= 0; j++) {
          if (diff[i-j] > 0) break;
          if (diff[i-j] != 0) visit[i-j] = true;
          len++;
        }
      }
      answer = Integer.max(answer, len);
    }
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
}
