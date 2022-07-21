import java.io.*;

public class BOJ1769 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    if (input.length == 1) {
      bw.write("0\n");
      bw.write((input[0]-'0')%3 == 0 ? "YES\n" : "NO\n");
      bw.flush();
      return;
    }

    int num = 1;
    int sum = 0;
    for (int i = 0; i < input.length; i++) {
      sum += input[i]-'0';
    }
    while (sum >= 10) {
      num++;
      int n = sum;
      sum = 0;
      while (n != 0) {
        sum += n%10;
        n /= 10;
      }
    }
    bw.write(Integer.toString(num));
    bw.newLine();
    bw.write(sum % 3 == 0 ? "YES\n" : "NO\n");
    bw.flush();
  }
}
