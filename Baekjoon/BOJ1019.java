import java.io.*;

public class BOJ1019 {
  static long[] answer;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    answer = new long[10];
    answer(n);
    for (int i = 0; i < 10; i++) {
      bw.write(Long.toString(answer[i]));
      bw.write(' ');
    }
    bw.flush();
  }
  static void calc(int n, int add) {
    if (n == 0) answer[0] += add;
    while (n > 0) {
      answer[n%10] += add;
      n /= 10;
    }
  }
  static int toNine(int n, int add) {
    if (n == 0) return 0;
    if (n < 9) {
      while (n > 0) calc(n--, add);
    }
    while (n > 0 && n%10 != 9) calc(n--, add);
    return n;
  }
  static void answer(int n) {
    int digit = 1;
    n = toNine(n, digit);
    while (n > 0) {
      for (int i = 0; i <= 9; i++) {
        answer[i] += ((n/10)+1)*digit;
      }
      n /= 10;
      answer[0] -= digit;
      digit *= 10;
      n = toNine(n, digit);
    }
  }
}
