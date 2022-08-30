import java.io.*;

public class BOJ2018 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int num = 0;
    long n1 = 1, n2 = 0;
    long ptr1 = 1, ptr2 = 0;
    while (ptr2 < ptr1) {
      if (ptr1 - ptr2 < n) {
        n1++;
        ptr1 += n1;
      } else if (ptr1 - ptr2 > n) {
        n2++;
        ptr2 += n2;
      }
      else {
        num++;
        n1++; n2++;
        ptr1 += n1;
        ptr2 += n2;
      }
    }

    bw.write(Integer.toString(num));
    bw.flush();
  }
}
