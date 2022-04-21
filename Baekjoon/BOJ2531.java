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
    int[] numPick = new int[d+1]; // 1 ~ d
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    int maxKind = 0;
    int kind = 0;
    
    // k pick: [0, ..., k-1]
    for (int i = 0; i < k; i++) {
      if (numPick[arr[i]] == 0) kind++;
      numPick[arr[i]]++;
    }
    if (numPick[c] == 0 && maxKind < (kind+1)) maxKind = kind+1;
    for (int ptr = 1; ptr < n; ptr++) {
      if (--numPick[arr[ptr-1]] == 0) kind--;
      if (numPick[arr[(ptr + k - 1) % n]]++ == 0) kind++;
      if (numPick[c] == 0 && maxKind < (kind+1)) maxKind = kind+1;
      if (maxKind < kind) maxKind = kind;
    }
    bw.write(Integer.toString(maxKind));
    bw.flush();
  }
}
