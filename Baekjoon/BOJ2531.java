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

    int[] arr = new int[n];
    int[] onPick = new int[d+1];
    boolean onCoupon = false;
    int totalKind = 0;
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
      if (arr[i] == c) onCoupon = true;
      if (onPick[arr[i]] == 0) totalKind++;
      onPick[arr[i]]++;
    }
    Arrays.fill(onPick, 0);
    int kind = 0;
    int maxKind = totalKind >= k ? k : totalKind;
    for (int i = 0; i < k; i++) {
      if (onPick[arr[i]] == 0) kind++;
      onPick[arr[i]]++;
    }
    if (((arr[n-1] == c || arr[k] == c) && onPick[c] == 0) || !onCoupon) {
      if (maxKind < kind+1) maxKind = kind+1;
    }
    if (maxKind < kind) maxKind = kind;
    int ptr = 0;
    // System.out.println("ptr: " + ptr + ", end: " + (k-1) + ", kind: " + kind);
    while (ptr < n) {
      onPick[arr[ptr]]--;
      if (onPick[arr[ptr]] == 0) kind--;
      int end = (ptr + k) % n;
      if (onPick[arr[end]] == 0) kind++;
      onPick[arr[end]]++;
      if (((arr[ptr] == c || arr[end] == c) && onPick[c] == 0) || !onCoupon) {
        // System.out.println("Here: " + (kind+1));
        if (maxKind < kind+1) maxKind = kind+1;
      }
      if (maxKind < kind) maxKind = kind;
      ptr++;
      // System.out.println("ptr: " + ptr + ", end: " + end + ", kind: " + kind);
    }
    bw.write(Integer.toString(maxKind));
    bw.flush();
  }
}
