import java.io.*;

public class BOJ10988 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    bw.write(isPalindrome(input) ? "1\n" : "0\n");
    bw.flush();
  }
  static boolean isPalindrome(char[] input) {
    for (int i = 0; i < input.length/2; i++) {
      if (input[i] != input[input.length-1-i]) return false;
    }
    return true;
  }
}
