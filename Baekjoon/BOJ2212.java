import java.io.*;
import java.util.*;

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
    Arrays.sort(censor);
    ArrayList<Integer> tmp = new ArrayList<>();
    int answer = 0;
    for (int i = 1; i < n; i++) {
      answer += censor[i] - censor[i-1];
      tmp.add(censor[i]- censor[i-1]);
    }
    Collections.sort(tmp, Collections.reverseOrder());
    int ptr = 0;
    int remainder = 1;
    while (ptr < n-1 && remainder < k) {
      answer -= tmp.get(ptr++);
      remainder++;
    }
    bw.write(Integer.toString(answer));
    bw.newLine();
    bw.flush();
  }
}
