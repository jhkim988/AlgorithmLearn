import java.io.*;

public class BOJ4354 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    char[] input = br.readLine().toCharArray();
    while (!(input[0] == '.' && input.length == 1)) {
      int last = skip(input);
      if (input.length % (input.length - last) != 0) bw.write("1");
      else bw.write(Integer.toString(input.length / (input.length - last)));
      bw.newLine();
      input = br.readLine().toCharArray();
    }
    bw.flush();
  }
  static int skip(char[] input) {
    int[] skip = new int[input.length];
    int pt = 1;
    int pp = 0;
    while (pt < input.length) {
      if (input[pt] == input[pp]) {
        skip[pt++] = ++pp;
      } else if (pp == 0) {
        skip[pt++] = 0;
      } else {
        pp = skip[pp - 1];
      }
    }
    return skip[input.length - 1];
  }
  static int getPi(char[] p) {
    int max = 0;
    int m = p.length;
    int j = 0;
    int[] pi = new int[m];
    for (int i = 1; i< m; i++) {
      while (j > 0 && p[i] != p[j])
        j = pi[j - 1];
      if (p[i] == p[j])
      {
        pi[i] = ++j;
        if (pi[i] > max)
          max = pi[i];
      }
    }
    return max;
  }
}
