import java.io.*;

public class BOJ1305 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int L = Integer.parseInt(br.readLine());
    char[] view = br.readLine().toCharArray();
    int result = shortestLen(view, L);
    bw.write(result + "\n");
    bw.flush();
  }
  static int shortestLen(char[] view, int L) {
    int[] pi = failure(view, L);
    return L - pi[L - 1];
  }
  static int[] failure(char[] view, int L) {
    int[] pi = new int[L];
    pi[0] = 0;
    int pt = 1;
    int pp = 0;
    while (pt < L) {
      if (view[pt] == view[pp]) {
        pp++;
        pi[pt] = pp;
        pt++;
      } else if (pp == 0) {
        pi[pt] = pp;
        pt++;
      } else {
        pp = pi[pp - 1];
      }
    }
    return pi;
  }
}
