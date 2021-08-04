import java.io.*;
import java.util.*;

public class BOJ1644 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int num = Integer.parseInt(br.readLine());
    ArrayList<Integer> prime = new ArrayList<>();
    prime.add(2);
    for (int i = 3; i <= num; i++) {
      boolean isPrime = true;
      if (i % 2 == 0) {
        isPrime = false;
      }
      for (int j = 3; j * j <= i; j += 2) {
        if (i % j == 0) {
          isPrime = false;
          break;
        }
      }

      if (isPrime) {
        prime.add(i);
      }
    }

    int ptr1 = 0;
    int ptr2 = 0;
    int partialSum = prime.get(0);
    int count = 0;
    int len = prime.size();
    
    while (ptr2 < len) {
      if (partialSum < num) {
        ptr2++;
        partialSum += ptr2 == len ? 0 : prime.get(ptr2);
      } else if (partialSum > num) {
        partialSum -= prime.get(ptr1);
        ptr1++;
      } else {
        count++;
        partialSum -= prime.get(ptr1);
        ptr1++;
      }
    }

    bw.write(count + "\n");
    bw.flush();
  }
}
