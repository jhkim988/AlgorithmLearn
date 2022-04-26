import java.io.*;
import java.util.*;

public class BOJ1017 {
  static int[] arr;
  static boolean[] isPrime, visit;
  static int[] group1, group2;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    arr = new int[n+1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    isPrime = new boolean[2_001];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i*i <= isPrime.length; i++) {
      if (!isPrime[i]) continue;
      for (int j = i+i; j < isPrime.length; j += i) {
        isPrime[j] = false;
      }
    }

    ArrayList<Integer> answer = new ArrayList<>();
    visit = new boolean[n+1];
    group1 = new int[n+1];
    group2 = new int[n+1];
    for (int i = 2; i <= n; i++) {
      // 1 -> i, i -> 1 match:
      if (!isPrime[arr[1] + arr[i]]) continue;
      int match = 2;
      Arrays.fill(group1, 0);
      Arrays.fill(group2, 0);
      group1[1] = i; group2[1] = i;
      group2[i] = 1; group1[i] = 1;
      for (int k = 2; k <= n; k++) {
        if (group1[k] != 0) continue;
        Arrays.fill(visit, false);
        if (dfs(k, i)) {
          match += 2;
        }
      }

      if (match == n) {
        answer.add(arr[group1[1]]);
      }
    }

    if (answer.size() == 0) {
      bw.write("-1\n");
    } else {
      Collections.sort(answer);
      bw.write(Integer.toString(answer.get(0)));
      for (int i = 1; i < answer.size(); i++) {
        bw.write(' ');
        bw.write(Integer.toString(answer.get(i)));
      }
      bw.newLine();
    }
    bw.flush();
  }
  static boolean dfs(int i, int avoid) {
    visit[i] = true;
    for (int j = 2; j < arr.length; j++) {
      if (i == j || j == avoid || !isPrime[arr[i]+arr[j]]) continue;
      if (group2[j] == 0 || (!visit[group2[j]] && dfs(group2[j], avoid))) {
        group1[i] = j; group2[i] = j;
        group2[j] = i; group1[j] = i;
        return true;
      }
    }
    return false;
  }
}
