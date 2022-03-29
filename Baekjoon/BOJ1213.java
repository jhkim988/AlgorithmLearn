import java.io.*;

public class BOJ1213 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int[] num = new int[26];
    for (int i = 0; i < input.length; i++) {
      num[input[i] - 'A']++;
    }
    int numOdd = 0;
    for (int i = 0; i < 26; i++) {
      if ((num[i] & 1) == 1) numOdd++;
    }
    if ((input.length & 1) == 0) {
      if (numOdd != 0) {
        bw.write("I'm Sorry Hansoo\n");
        bw.flush();
        return;
      }      
    } else {
      if (numOdd != 1) {
        bw.write("I'm Sorry Hansoo\n");
        bw.flush();
        return;
      }
    }
    char[] output = new char[input.length];
    int ptr1 = 0;
    int ptr2 = input.length-1;
    for (int i = 0; i < 26; i++) {
      if (num[i] == 0) continue;
      
    }
  }
}
