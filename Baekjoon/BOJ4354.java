import java.io.*;

public class BOJ4354 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    String input = br.readLine();
    int len = input.length();
    while (!(len == 1 && input.charAt(0) =='.')) {
      int result = root(input, len);
      bw.write(result + "\n");
      input = br.readLine();
      len = input.length();
    }
    bw.flush();
  }
  static int root(String str, int len) {
    int result = 1;
    char[] text = str.toCharArray();
    for (int i = 2; i <= len; i++) {
      if (len % i != 0) continue;
      char[] pat = new char[len / i];
      for (int j = 0; j < len/i; j++) {
        pat[j] = text[j];
      }
      if (!kmpMatch(text, pat)) continue;
      result = i;
    }
    return result;
  }
  static boolean kmpMatch(char[] text, char[] pat) {
    for (int ptr1 = pat.length; ptr1 < text.length; ptr1 += pat.length) {
      for (int ptr2 = 0; ptr2 < pat.length; ptr2++) {
        if (text[ptr1 + ptr2] != pat[ptr2]) {
          return false;
        }
      }
    } 
    return true;
  }
}
