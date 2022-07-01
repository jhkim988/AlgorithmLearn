import java.io.*;

public class BOJ13506 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] input = br.readLine().toCharArray();
    int[] pi = pi(input);
    
    nextPattern: for (int patlen = input.length-2; patlen >= 1; patlen--) {
      // last:
      for (int i = 0; i < patlen; i++) {
        if (input[i] != input[input.length-patlen+i]) continue nextPattern;
      }
      // middle:
      int j = 0;
      for (int i = 1; i < input.length-1; i++) {
        while (j > 0 && input[i] != input[j]) j = pi[j-1];
        if (input[i] == input[j]) {
          if (j == patlen-1) {
            for (int k = 0; k < patlen; k++) {
              bw.write(input[k]);
            }
            bw.flush();
            return;
          } else {
            j++;
          }
        }
      }
    }
    bw.write("-1");
    bw.flush();
  }
  static int[] pi(char[] arr) {
    int[] pi = new int[arr.length];
    int j = 0;
    for (int i = 1; i < arr.length; i++) {
      while (j > 0 && arr[i] != arr[j]) j = pi[j-1];
      if (arr[i] == arr[j]) pi[i] = ++j;
    }
    return pi;
  }
}
