import java.io.*;

public class BOJ16968 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] format = br.readLine().toCharArray();
    int answer = bruteForce(format);
    bw.write(answer + "\n");
    bw.flush();
  }  
  static int bruteForce(char[] format) {
    return recur(format, 0, '.');
  }
  static int recur(char[] format, int depth, char prev) {
    if (depth >= format.length) {
      return 1;
    }
    int sum = 0;
    if (format[depth] == 'c') {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        if (prev == ch) continue;
        sum += recur(format, depth + 1, ch);
      }
    } else { // format[depth] == 'd'
      for (char ch = '0'; ch <= '9'; ch++) {
        if (prev == ch) continue;
        sum += recur(format, depth + 1, ch);
      }
    }
    return sum;
  }
}
