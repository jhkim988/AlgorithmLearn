import java.io.*;

public class BOJ1254 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    char[] arr = br.readLine().toCharArray();
    int findIdx = arr.length;
    next: for (int i = 0; i < arr.length; i++) {
      for (int j = arr.length-1; j >= 0 && i+arr.length-1-j < arr.length; j--) {
        if (arr[i+arr.length-1-j] != arr[j]) continue next;
      }
      findIdx = i;
      break;      
    }
    bw.write(Integer.toString(findIdx + arr.length));
    bw.flush();
  }
}
