import java.io.*;
import java.util.*;

public class BOJ2290 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int size = Integer.parseInt(st.nextToken());
    char[] display = st.nextToken().toCharArray();

    char[][] print = new char[2*size + 3][(size + 3) * display.length - 1];
    for (int i = 0; i < print.length; i++) Arrays.fill(print[i], ' ');
    for (int i = 0; i < display.length; i++) {
      display(display[i], print, size, (size + 3) * i, (size + 3) * (i + 1) - 1);
    }

    for (int i = 0; i < print.length; i++) {
      bw.write(print[i]);
      bw.write('\n');
    }
    bw.flush();
  }
  static void display(char src, char[][] print, int size, int from, int to) {
    switch (src) {
      case '1': {
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        break;
      }
      case '2': {
        horizontal(print, from + 1, to - 1, 0);
        horizontal(print, from + 1, to - 1, size + 1);
        horizontal(print, from + 1, to - 1, 2 * size + 2);
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, from);
        break;
      }
      case '3': {
        horizontal(print, from + 1, to - 1, 0);
        horizontal(print, from + 1, to - 1, size + 1);
        horizontal(print, from + 1, to - 1, 2 * size + 2);
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        break;
      }
      case '4': {
        vertical(print, 1, size + 1, from);
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        horizontal(print, from + 1, to - 1,size + 1);
        break;
      }
      case '5': {
        horizontal(print, from + 1, to - 1, 0);
        horizontal(print, from + 1, to - 1, size + 1);
        horizontal(print, from + 1, to - 1, 2 * size + 2);
        vertical(print, 1, size + 1, from);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        break;
      }
      case '6': {
        horizontal(print, from + 1, to - 1, 0);
        horizontal(print, from + 1, to - 1, size + 1);
        horizontal(print, from + 1, to - 1, 2 * size + 2);
        vertical(print, 1, size + 1, from);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        vertical(print, size + 2, 2 * size + 2, from);
        break;
      }
      case '7': {
        horizontal(print, from + 1, to - 1, 0);
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        break;
      }
      case '8': {
        horizontal(print, from + 1, to - 1, 0);
        horizontal(print, from + 1, to - 1, size + 1);
        horizontal(print, from + 1, to - 1, 2 * size + 2);
        vertical(print, 1, size + 1, from);
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, from);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        break;
      }
      case '9': {
        horizontal(print, from + 1, to - 1, 0);
        horizontal(print, from + 1, to - 1, size + 1);
        horizontal(print, from + 1, to - 1, 2 * size + 2);
        vertical(print, 1, size + 1, from);
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, to - 1);
        break;
      }
      default : {
        horizontal(print, from + 1, to - 1, 0);
        horizontal(print, from + 1, to - 1, 2 * size + 2);
        vertical(print, 1, size + 1, from);
        vertical(print, 1, size + 1, to - 1);
        vertical(print, size + 2, 2 * size + 2, from);
        vertical(print, size + 2, 2 * size + 2, to - 1);
      }
    }
  }
  static void horizontal(char[][] print, int from, int to, int pos) {
    for (int idx = from; idx < to; idx++) {
      print[pos][idx] = '-';
    }
  }
  static void vertical(char[][] print, int from, int to, int pos) {
    for (int idx = from; idx < to; idx++) {
      print[idx][pos] = '|';
    }
  }
}
