import java.io.*;
import java.util.*;

public class BOJ1251 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] str = br.readLine().toCharArray();
    
    char[] tmp = new char[str.length];
    char[] min = new char[str.length];
    Arrays.fill(min, 'z');

    for (int ptr1 = 0; ptr1+1 < str.length; ptr1++) {
      for (int ptr2 = ptr1+1; ptr2+1 < str.length; ptr2++) {
        int tmpPtr = 0;
        for (int i = ptr1; i >= 0; i--) {
          tmp[tmpPtr++] = str[i];
        }
        for (int i = ptr2; i > ptr1; i--) {
          tmp[tmpPtr++] = str[i];
        }
        for (int i = str.length-1; i > ptr2; i--) {
          tmp[tmpPtr++] = str[i];
        }
        if (compare(tmp, min) < 0) {
          System.arraycopy(tmp, 0, min, 0, str.length);
        }
      }
    }
    bw.write(min);
    bw.flush();
  }
  static int compare(char[] a, char[] b) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] < b[i]) return -1;
      else if (b[i] < a[i]) return 1;
    }
    return 0;
  }
}
