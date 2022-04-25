import java.io.*;
import java.util.*;

public class BOJ16139 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int[][] psum = new int['z'-'a'+1][input.length];
    psum[input[0]-'a'][0] = 1;
    for (int i = 1; i < input.length; i++) {
      int ch = input[i]-'a';
      for (char c = 0; c <= 'z'-'a'; c++) {
        if (c == ch) psum[c][i] = psum[c][i-1] + 1;
        else psum[c][i] = psum[c][i-1];
      }
    }
    int q = Integer.parseInt(br.readLine());
    while (q-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int ch = st.nextToken().charAt(0) - 'a';
      int lo = Integer.parseInt(st.nextToken());
      int hi = Integer.parseInt(st.nextToken());
      bw.write(Integer.toString(psum[ch][hi]- (lo == 0 ? 0 : psum[ch][lo-1])));
      bw.newLine();
    }
    bw.flush();
  }
}
