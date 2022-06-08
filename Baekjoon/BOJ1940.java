import java.io.*;
import java.util.*;

public class BOJ1940 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);
    int num = 0;
    int ptr1 = 0, ptr2 = n-1;
    while (ptr1 < ptr2) {
      if (arr[ptr1] + arr[ptr2] < m) {
        ptr1++;
      } else if (arr[ptr1] + arr[ptr2] > m) {
        ptr2--;
      } else {
        num++;
        ptr1++;
        ptr2--;
      }
    }
    bw.write(Integer.toString(num));
    bw.flush();
  }
}
/*
 * 죽는 날까지 하늘을 우러러
 * 한 점 부끄럼 없기를
 * 잎새에 이는 바람에도
 * 나는 괴로워했다
 * 별을 노래하는 마음으로
 * 모든 죽어가는 것을 사랑해야지
 * 그리고 나한테 주어진 길을 걸어가야겠다.
 * 오늘 밤도 별이 바람에 스치운다
 */