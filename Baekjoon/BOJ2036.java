import java.io.*;
import java.util.*;

public class BOJ2036 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int one = 0;
    int zero = 0;
    ArrayList<Long> positive = new ArrayList<>();
    ArrayList<Long> negative = new ArrayList<>();
    while (n-- > 0) {
      long input = Integer.parseInt(br.readLine());
      if (input > 1) positive.add(input);
      else if (input == 1) one++;
      else if (input < 0) negative.add(input);
      else zero++;
    }
    Collections.sort(positive, Collections.reverseOrder());
    Collections.sort(negative);
    long answer = one;
    int ptr = 0;
    while (ptr+1 < positive.size()) {
      answer += positive.get(ptr) * positive.get(ptr+1);
      ptr += 2;
    }
    if (ptr < positive.size()) answer += positive.get(ptr);
    ptr = 0;
    while (ptr+1 < negative.size()) {
      answer += negative.get(ptr) * negative.get(ptr+1);
      ptr += 2;
    }
    if (ptr < negative.size()) {
      if (zero == 0) answer += negative.get(ptr); 
    }
    bw.write(Long.toString(answer));
    bw.newLine();
    bw.flush();
  }  
}
