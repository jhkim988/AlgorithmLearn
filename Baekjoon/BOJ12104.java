import java.io.*;

public class BOJ12104 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str = br.readLine().toCharArray();
    char[] pat = br.readLine().toCharArray();
    int[] pi = pi(pat);
    int j = 0;
    int num = 0;
    for (int i = 0; i < str.length*2-1; i++) {
      while (j > 0 && str[i%str.length] != pat[j]) j = pi[j-1];
      if (str[i%str.length] == pat[j]) {
        if (j == pat.length-1) {
          j = pi[j];
          num++;
        } else {
          j++;
        }
      }
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
  static int[] pi(char[] arr) {
    int[] pi = new int[arr.length];
    int j = 0;
    for (int i = 1; i < arr.length; i++) {
      while (j > 0 && arr[i] != arr[j]) j = pi[j-1];
      if (arr[i] == arr[j]) pi[i] = ++j;
    }
    return pi;
  }
}
