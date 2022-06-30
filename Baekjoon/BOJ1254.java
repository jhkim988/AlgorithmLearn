import java.io.*;

public class BOJ1254 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] arr = br.readLine().toCharArray();
    int[] pi = new int[arr.length];
    int j = 0;
    for (int i = 1; i < arr.length; i++) {
      while (j > 0 && arr[arr.length-1-i] != arr[arr.length-1-j]) j = pi[j-1];
      if (arr[arr.length-1-i] == arr[arr.length-1-j]) pi[i] = ++j;
    }
    j = 0;
    for (int i = 0; i < arr.length; i++) {
      while (j > 0 && arr[i] != arr[arr.length-1-j]) j = pi[j-1];
      if (arr[i] == arr[arr.length-1-j]) j++;
    }
    bw.write(Integer.toString(2*arr.length-j));
    bw.flush();
  }
}
