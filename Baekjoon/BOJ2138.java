import java.io.*;

public class BOJ2138 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Integer.parseInt(br.readLine());
    char[] start = br.readLine().toCharArray();
    char[] target = br.readLine().toCharArray();
    char[] copy = start.clone();
    flip(copy, 0);
    int result1 = traverse(start, target);
    int result2 = traverse(copy, target) + 1;
    if (result1 < 0 && result2 < 0) {
      bw.write("-1\n");
    } else {
      if (result1 < 0) {
        bw.write(result2 + "\n");
      } else if (result2 < 0) {
        bw.write(result1 + "\n");
      } else {
        bw.write(Math.min(result1, result2) + "\n");
      }
    }
    bw.flush();
  }
  static int traverse(char[] start, char[] target) {
    int num = 0;
    for (int i = 0; i < start.length; i++) {
      if (start[i] != target[i]) {
        if (i + 1 >= start.length) continue;
        flip(start, i + 1);
        num++;
      }
    }
    for (int i = 0; i < start.length; i++) {
      if (start[i] != target[i]) return -10;
    }
    return num;
  }
  static void flip(char[] src, int idx) {
    if (idx < 0 || idx >= src.length) return;
    for (int i = idx - 1; i <= idx + 1; i++) {
      if (i < 0 || i >= src.length) continue; 
      src[i] = src[i] == '0' ? '1' : '0';
    }
  }
}
