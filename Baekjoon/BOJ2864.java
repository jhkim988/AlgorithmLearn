import java.io.*;
import java.util.*;

public class BOJ2864 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    char[] a = st.nextToken().toCharArray();
    char[] b = st.nextToken().toCharArray();
    change(a, '5', '6');
    change(b, '5', '6');
    int max = add(a, b);
    change(a, '6', '5');
    change(b, '6', '5');
    int min = add(a, b);
    bw.write(Integer.toString(min));
    bw.write(' ');
    bw.write(Integer.toString(max));
    bw.flush();
  }
  static void change(char[] arr, char prev, char next) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == prev) arr[i] = next;
    }
  }
  static int add(char[] a, char[] b) {
    int exp = 1;
    int aval = 0;
    for (int i = a.length-1; i >= 0; i--) {
      aval += (a[i]-'0')*exp;
      exp *= 10;
    }
    exp = 1;
    int bval = 0;
    for (int i = b.length-1; i >= 0; i--) {
      bval += (b[i]-'0')*exp;
      exp *= 10;
    }
    return aval + bval;
  }
}
