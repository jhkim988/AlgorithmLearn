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
    if (numOdd > 1) {
      bw.write("I'm Sorry Hansoo\n");
        bw.flush();
        return;
    } else if (numOdd == 1) {
      if ((input.length & 1) == 0) {
        bw.write("I'm Sorry Hansoo\n");
        bw.flush();
        return;
      }
    }
    char[] output = new char[input.length];
    int ptr1 = 0;
    int ptr2 = input.length-1;
    int idx = 0;
    for (char i = 0; i < 26; i++) {
      if (num[i] == 0) continue;
      if ((num[i] & 1) == 1) {
        idx = i;
      }
      while (num[i] > 1) {
        output[ptr1++] = output[ptr2--] = (char) (i + 'A');
        num[i] -= 2;
      }
    }
    if (numOdd == 1) {
      output[ptr1] = (char) (idx + 'A');
    }
    bw.write(output);
    bw.newLine();
    bw.flush();
  }
}
