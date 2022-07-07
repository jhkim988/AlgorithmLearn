import java.io.*;

public class BOJ3356 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    char[] str = br.readLine().toCharArray();
    int[] pi = new int[n];
    int j = 0;
    for (int i = 1; i < n; i++) {
      while (j > 0 && str[i] != str[j]) j = pi[j-1];
      if (str[i] == str[j]) pi[i] = ++j;
    }
    bw.write(Integer.toString(n-pi[n-1]));
    bw.flush();
  }
}