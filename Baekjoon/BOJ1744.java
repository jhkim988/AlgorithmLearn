import java.io.*;
import java.util.*;

public class BOJ1744 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int len = Integer.parseInt(br.readLine());
    ArrayList<Integer> largerThanOne = new ArrayList<>();
    ArrayList<Integer> nonPositive = new ArrayList<>();
    int sum = 0;
    for (int i = 0; i < len; i++) {
      int tmp = Integer.parseInt(br.readLine());
      if (tmp > 1) {
        largerThanOne.add(tmp);
      } else if (tmp == 1) {
        sum++;
      } else {
        nonPositive.add(tmp);
      }
    }

    Collections.sort(largerThanOne, Collections.reverseOrder());
    Collections.sort(nonPositive);
    sum += calc(largerThanOne) + calc(nonPositive);
    bw.write(sum + "\n");
    bw.flush();    
  }
  static int calc(ArrayList<Integer> arr) {
    int len = arr.size();
    int sum = 0;
    for (int i = 0; i < len - 1; i+= 2) {
      sum += arr.get(i) * arr.get(i + 1);
    }
    if (len % 2 == 1) {
      sum += arr.get(len - 1);
    }
    return sum;
  }
} 
