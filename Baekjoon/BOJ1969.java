import java.io.*;
import java.util.*;

public class BOJ1969 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    char[][] input = new char[n][m];
    for (int i = 0; i < n; i++) {
      input[i] = br.readLine().toCharArray();
    }
    char[] type = {'A', 'C', 'G', 'T'};
    int[] ntype = new int[4];
    char[] answer = new char[m];
    int len = 0;
    for (int i = 0; i < m; i++) {
      ntype[0] = ntype[1] = ntype[2] = ntype[3] = 0;
      for (int j = 0; j < n; j++) {
        if (input[j][i] == 'A') ntype[0]++;
        else if (input[j][i] == 'C') ntype[1]++;
        else if (input[j][i] == 'G') ntype[2]++;
        else ntype[3]++;
      }
      int maxIdx = 0;
      for (int j = 0; j < 4; j++) {
        if (ntype[maxIdx] < ntype[j]) maxIdx = j;
      }
      answer[i] = type[maxIdx];
      for (int j = 0; j < 4; j++) {
        if (j == maxIdx) continue;
        len += ntype[j];
      }
    }
    bw.write(answer);
    bw.newLine();
    bw.write(Integer.toString(len));
    bw.flush();
  }
}
