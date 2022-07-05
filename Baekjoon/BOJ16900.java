import java.io.*;
import java.util.*;

public class BOJ16900 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    char[] str = st.nextToken().toCharArray();
    long k = Integer.parseInt(st.nextToken());
    int[] pi = new int[str.length];
    int j = 0;
    for (int i = 1; i < str.length; i++) {
      while (j > 0 && str[i] != str[j]) j = pi[j-1];
      if (str[i] == str[j]) pi[i] = ++j;
    }
    int n = str.length;
    long x = pi[n-1];
    long answer = 0;
    if (k > 1) {
      answer = (n-x)*(k-1) + n;
    } else {
      answer = n;
    }
    bw.write(Long.toString(answer));
    bw.flush();
  }
}
