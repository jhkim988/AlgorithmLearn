import java.io.*;
import java.util.*;

public class BOJ1450 {
  static ArrayList<Long> numCaseLeft = new ArrayList<>();
  static ArrayList<Long> numCaseRight = new ArrayList<>();
  static int numThing;
  static Long capacity;
  static long[] thing;
  static void findNum(int start, int end, long sum, ArrayList<Long> numCase) {
    if (sum > capacity) {
      return;
    }
    if (start == end) {
      if (sum != 0) {
        numCase.add(sum);
      }
      return;
    }
    findNum(start + 1, end, sum, numCase);
    findNum(start + 1, end, sum + thing[start], numCase);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    numThing = Integer.parseInt(st.nextToken());
    capacity = Long.parseLong(st.nextToken());

    thing = new long [numThing];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < numThing; i++) {
      thing[i] = Long.parseLong(st.nextToken());
    }

    findNum(0, numThing / 2, 0L, numCaseLeft);
    findNum(numThing / 2, numThing, 0L, numCaseRight);
    
    long ans = numCaseLeft.size() + numCaseRight.size();

    Collections.sort(numCaseRight);

    for (int i = 0; i < numCaseLeft.size(); i++) {
      long diff = capacity - numCaseLeft.get(i);
      int pos = Collections.binarySearch(numCaseRight, diff);
      if (pos < 0) {
        pos = -(pos + 1);
      } else {
        while (pos < numCaseRight.size() && numCaseRight.get(pos) == diff) {
          pos++;
        }
      }
      ans += pos;
    }
    ans++; // empty case
    bw.write(ans + "\n");
    bw.flush();
  }
}
