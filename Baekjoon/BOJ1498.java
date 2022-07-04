import java.io.*;

public class BOJ1498 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str = br.readLine().toCharArray();
    int[] pi = pi(str);
    for (int i = 1; i < str.length; i++) {
      if ((i+1)%(i+1-pi[i]) != 0) continue;
      if ((i+1)/(i+1-pi[i]) == 1) continue;
      bw.write(Integer.toString(i+1));
      bw.write(' ');
      bw.write(Integer.toString((i+1)/(i+1-pi[i])));
      bw.newLine();
    }
    bw.flush();
  }
  static int[] pi(char[] str) {
    int[] pi = new int[str.length];
    int j = 0;
    for (int i = 1; i < str.length; i++) {
      while (j > 0 && str[i] != str[j]) j = pi[j-1];
      if (str[i] == str[j]) pi[i] = ++j;
    }
    return pi;
  }
}
