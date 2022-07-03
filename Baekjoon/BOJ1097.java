import java.io.*;

public class BOJ1097 {
  static int n, len, k;
  static char[][] strs;
  static int[] permutation, pi;
  static boolean[] visit;
  static char[] str;
  static int answer = 0;
  public static void main(String[] argss) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    strs = new char[n][];
    for (int i = 0; i < n; i++) {
      strs[i] = br.readLine().toCharArray();
      len += strs[i].length;
    }
    k = Integer.parseInt(br.readLine());
    permutation = new int[n];
    visit = new boolean[n];
    str = new char[len];
    pi = new int[len];
    permutation(1);
    bw.write(Integer.toString(answer*n));
    bw.flush();
  }
  static boolean magicString() {
    int ptr = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < strs[permutation[i]].length; j++) {
        str[ptr++] = strs[permutation[i]][j];
      }
    }
    int j = 0;
    for (int i = 1; i < len; i++) {
      while (j > 0 && str[i] != str[j]) j = pi[j-1];
      if (str[i] == str[j]) pi[i] = ++j;
    }
    int num = 0;
    j = 0;
    for (int i = 0; i < 2*len-1; i++) {
      while (j > 0 && str[i%len] != str[j]) j = pi[j-1];
      if (str[i%len] == str[j]) {
        if (j == len-1) {
          j = pi[j];
          num++;
          if (num > k) return false;
        } else j++;
      }
    }
    return num == k;
  }
  static void permutation(int depth) {
    if (depth == n) {
      if(magicString()) answer++;
      return;
    }
    for (int i = 1; i < n; i++) {
      if (visit[i]) continue;
      visit[i] = true;
      permutation[depth] = i;
      permutation(depth+1);
      visit[i] = false;
    }
  }
}
