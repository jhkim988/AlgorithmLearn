import java.io.*;
import java.util.*;

public class BOJ9489 {
  static private Queue<Integer> que = new LinkedList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    while (n != 0 && k != 0) {
      st = new StringTokenizer(br.readLine());
      int[] input = new int[n];
      for (int i = 0; i < n; i++) {
        input[i] = Integer.parseInt(st.nextToken());
      }
      bw.write(Integer.toString(numSibling(input, k)));
      bw.newLine();
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());
    }
    bw.flush();
  }
  static int numSibling(int[] arr, int k) {
    if (arr.length == 1) return 0;
    int[] tree = new int[arr.length];
    int[] numChild = new int[arr.length];
    Arrays.fill(tree, -1);
    que.clear();
    que.add(0);
    int kIdx = 0;
    int left = 1, right = 1;
    while (left < arr.length && right < arr.length) {
      int parent = que.poll();
      for (; right+1 < arr.length && arr[right]+1 == arr[right+1]; right++);
      for (int i = left; i <= right; i++) {
        if (arr[i] == k) kIdx = i; 
        tree[i] = parent;
        numChild[parent]++;
        que.add(i);
      }
      right++;
      left = right;
    }
    if (tree[kIdx] == -1) return 0;
    if (tree[tree[kIdx]] == -1) return 0;
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      if (tree[i] != tree[tree[kIdx]] || i == kIdx) continue;
      sum += numChild[i];
    }
    return sum - numChild[tree[kIdx]];
  }
}