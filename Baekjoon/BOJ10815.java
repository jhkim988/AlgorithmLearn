import java.io.*;
import java.util.*;

public class BOJ10815 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] cardList = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      cardList[i] = Integer.parseInt(st.nextToken());
    }

    int M = Integer.parseInt(br.readLine());
    int[] checkList = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      checkList[i] = Integer.parseInt(st.nextToken());
    }

    StringBuilder sb = useBinSearch(cardList, checkList);
    bw.write(sb.toString());
    bw.flush();
  }
  static StringBuilder useBinSearch(int[] cardList, int[] checkList) {
    StringBuilder sb = new StringBuilder();
    Arrays.sort(cardList);
    for (int i = 0; i < checkList.length; i++) {
      int idx = Arrays.binarySearch(cardList, checkList[i]);
      if (idx < 0) {
        sb.append(0).append(' ');
      } else {
        sb.append(1).append(' ');
      }
    }
    sb.append('\n');
    return sb;
  }
}
