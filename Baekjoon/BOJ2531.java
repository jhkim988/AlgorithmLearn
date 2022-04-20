import java.io.*;
import java.util.*;

public class BOJ2531 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    int totalKind = 0;
    boolean couponOnBelt = false;
    int[] arr = new int[n];
    int[] numPick = new int[d+1]; // 1 ~ d
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
      if (arr[i] == c) couponOnBelt = true;
      if (numPick[arr[i]] == 0) totalKind++;
      numPick[arr[i]]++;
    }
    Arrays.fill(numPick, 0);
    int maxKind = totalKind >= k ? k : totalKind + (couponOnBelt ? 0 : 1);
    int kind = 0;
    
    // k pick: [0, ..., k-1]
    for (int i = 0; i < k; i++) {
      if (numPick[arr[i]] == 0) kind++;
      numPick[arr[i]]++;
    }
    
  }
}
