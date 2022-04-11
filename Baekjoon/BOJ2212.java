import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ2212 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int k = Integer.parseInt(br.readLine());
    int[] censor = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      censor[i] = Integer.parseInt(st.nextToken());
    }
    censor = IntStream.of(censor).sorted().distinct().toArray();
    System.out.println(Arrays.toString(censor));
    int answer = greedy(censor, 0, k);
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
  static int greedy(int[] arr, int start, int remainder) {
    if (start == arr.length) {
      return 3_000_000;
    }
    if (remainder == 1) {
      System.out.println("[" + start + ", " + (arr.length-1) + "]");
      return arr[arr.length-1] - arr[start];
    }
    double num = 1;
    double density = 0;
    int idx = start;
    for (int i = start+1; i < arr.length; i++) {
      num++;
      if (density <= num/(arr[i]-arr[start])) {
        idx = i;
        density = num/(arr[i]-arr[start]); 
      } 
    }
    System.out.println("start: " + start + ", density: " + density + ", idx: " + idx);
    int zero = greedy(arr, start+1, remainder-1);
    int nonzero = arr[idx]-arr[start] + greedy(arr, idx+1, remainder-1);
    if (nonzero < zero) {
      System.out.println("[" + start + ", " + idx + "]");
      return nonzero;
    } else {
      System.out.println("[" + start + ", " + start + "]");
      return zero;
    }
  }
}
