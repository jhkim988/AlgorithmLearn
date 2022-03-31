import java.io.*;

public class BOJ5585 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = 1000 - Integer.parseInt(br.readLine());
    int[] arr = {500, 100, 50, 10, 5, 1};
    int tot = 0;
    for (int key : arr) {
      int num = n / key;
      tot += num;
      n = n - num*key;
    }
    bw.write(Integer.toString(tot));
    bw.newLine();
    bw.flush();
  }
}
