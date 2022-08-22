import java.io.*;

public class BOJ1417 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n ;i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    bw.write(Integer.toString(solution1(arr)));
    bw.flush();
  }
  static int solution1(int[] arr) {
    int num = 0;
    while (true) {
      int maxIdx = 0;
      for (int i = 0; i < arr.length; i++) {
        if (arr[maxIdx] <= arr[i]) maxIdx = i;
      }
      if (maxIdx == 0) return num;
      arr[0]++;
      arr[maxIdx]--;
      num++;
    }
  }
}
