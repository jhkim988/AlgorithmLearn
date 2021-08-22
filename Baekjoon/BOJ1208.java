import java.io.*;
import java.util.*;

public class BOJ1208 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    ArrayList<Integer> leftPart = new ArrayList<>();
    ArrayList<Integer> rightPart = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N / 2; i++) {
      leftPart.add(Integer.parseInt(st.nextToken()));
    }
    for (int i = 0; i < (N - N/2); i++) {
      rightPart.add(Integer.parseInt(st.nextToken()));
    }

    ArrayList<Integer> leftSum = new ArrayList<>();
    ArrayList<Integer> rightSum = new ArrayList<>();
    bruteForce(leftPart, leftSum);
    bruteForce(rightPart, rightSum);

    Collections.sort(leftSum);
    Collections.sort(rightSum);
    long count = 0;
    count += numTarget(leftSum, S);
    count += numTarget(rightSum, S);
    for (int el : leftSum) {
      count += numTarget(rightSum, S - el);
    }
    bw.write(count + "\n");
    bw.flush();
  }
  static void bruteForce(ArrayList<Integer> part, ArrayList<Integer> sumList) {
    int len = part.size();
    boolean[] marked = new boolean[len];
    recur(0, marked, len, 0, part, sumList);
  }
  static void recur(int start, boolean[] marked, int len, int sum, ArrayList<Integer> part, ArrayList<Integer> sumList) {
    for (int i = start; i < len; i++) {
      if (marked[i]) continue;
      marked[i] = true;
      int newSum = sum + part.get(i);
      sumList.add(newSum);
      recur(i + 1, marked, len, newSum, part, sumList);
      marked[i] = false; 
    }
  }
  static int bound(ArrayList<Integer> list, int target, boolean isUpper) {
    // [ptr1, ptr2)
    int ptr1 = 0;
    int ptr2 = list.size();
    while (ptr1 < ptr2) {
      int mid = (ptr1 + ptr2) / 2;
      if (isUpper) { // upper bound
        if (list.get(mid) <= target) {
          ptr1 = mid + 1;
        } else {
          ptr2 = mid;
        }
      } else { // lower bound
        if (list.get(mid) >= target) {
          ptr2 = mid;
        } else {
          ptr1 = mid + 1;
        }
      }
    }
    return ptr2;
  }
  static int numTarget(ArrayList<Integer> list, int target) {
    return bound(list, target, true) - bound(list, target, false);
  }
}
