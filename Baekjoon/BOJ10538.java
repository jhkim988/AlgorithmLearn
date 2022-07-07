import java.io.*;
import java.util.*;

public class BOJ10538 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int hp = Integer.parseInt(st.nextToken());
    int wp = Integer.parseInt(st.nextToken());
    int hm = Integer.parseInt(st.nextToken());
    int wm = Integer.parseInt(st.nextToken());
    char[][] pat = new char[hp][wp];
    for (int i = 0; i < hp; i++) {
      pat[i] = br.readLine().toCharArray();
    }
    char[][] str = new char[hm][wm];
    for (int i = 0; i < hm; i++) {
      str[i] = br.readLine().toCharArray();
    }

    int[] piHorizon = new int[wp];
    int[] piVertical = new int[hp];
    
    int j = 0;
    for (int i = 1; i < wp; i++) {
      while (j > 0 && equal(pat, 0, i, pat, 0, j, hp, true)) j = piHorizon[j-1];
      if (equal(pat, 0, i, pat, 0, j, hp, true)) piHorizon[i] = ++j;
    }
    j = 0;
    for (int i = 1; i < hp; i++) {
      while (j > 0 && equal(pat, i, 0, pat, j, 0, wp, false)) j = piVertical[j-1];
      if (equal(pat, i, 0, pat, j, 0, wp, false)) piVertical[i] = ++j;
    }

    int num = 0;
    for (int i = 0; i <= hm-hp; i++) {
      j = 0;
      for (int jj = 0; jj < wm; jj++) {
        while (j > 0 && !equal(str, i, jj, pat, 0, j, hp)) j = pi[j-1];
        if (equal(str, i, jj, pat, 0, j, hp)) {
          if (j == wp-1) {
            j = pi[j];
            num++;
          } else {
            j++;
          }
        }
      }
    }

    bw.write(Integer.toString(num));
    bw.flush();
  }
  static boolean equal(char[][] a, int arow, int acol, char[][] b, int brow, int bcol, int n, boolean isVertical) {
    if (isVertical) {
      for (int i = 0; i < n; i++) {
        if (a[arow+i][acol] != b[brow+i][bcol]) return false;
      }
    } else {
      for (int i = 0; i < n; i++) {
        if (a[arow][acol+i] != a[brow][bcol+i]) return false;
      }
    }
    return true;
  }
}
