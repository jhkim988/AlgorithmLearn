import java.io.*;

public class BOJ17609 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      char[] input = br.readLine().toCharArray();
      bw.write(Integer.toString(palindromeTest(input)));
      bw.newLine();
    }
    bw.flush();
  }
  static int palindromeTest(char[] str) {
    for (int i = 0; i < str.length/2; i++) {
      if (str[i] == str[str.length-1-i]) continue;
      if (isPseudoPalindrome(str, i) || isPseudoPalindrome(str, str.length-1-i)) return 1;
      return 2;
    }
    return 0; // true
  }
  static boolean isPseudoPalindrome(char[] str, int skip) {
    int ptr1 = 0, ptr2 = str.length-1;
    while (ptr1 < ptr2) {
      if (ptr1 == skip) {
        ptr1++;
      }
      if (ptr2 == skip) {
        ptr2--;
      }
      if (str[ptr1] != str[ptr2]) return false;
      ptr1++; ptr2--;
    }
    return true;
  }
}