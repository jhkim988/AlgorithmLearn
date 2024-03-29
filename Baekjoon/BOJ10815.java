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

    StringBuilder sb = new StringBuilder();
    boolean[] result = useBinSearch(cardList, checkList);
    for (int i = 0; i < M; i++) {
      if (result[i]) {
        sb.append(1).append(' ');
      } else {
        sb.append(0).append(' ');
      }
    }
    sb.append('\n');
    bw.write(sb.toString());
    bw.flush();
  }
  static boolean[] useBinSearch(int[] cardList, int[] checkList) {
    boolean[] result = new boolean[checkList.length];
    Arrays.sort(cardList);
    for (int i = 0; i < checkList.length; i++) {
      int idx = Arrays.binarySearch(cardList, checkList[i]);
      if (idx < 0) continue;
      result[idx] = true;
    }
    return result;
  }
  static boolean[] useTwoPointer(int[] cardList, int[] checkList) {
    boolean[] result = new boolean[checkList.length];
    Arrays.sort(cardList);
    Arrays.sort(checkList);
    int ptr0 = 0;
    int ptr1 = 0;

    while (ptr0 < cardList.length && ptr1 < checkList.length) {
      if (cardList[ptr0] < checkList[ptr1]) {
        ptr0++;
      } else if (checkList[ptr1] < checkList[ptr0]) {
        ptr1++;
      } else {
        result[ptr0] = true;
        ptr0++;
        ptr1++;
      }
    }
    return result;
  }
}
