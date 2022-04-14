import java.io.*;
import java.util.*;

public class BOJ2036 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int zero = 0;
    ArrayList<Long> positive = new ArrayList<>();
    ArrayList<Long> negative = new ArrayList<>();
    while (n-- > 0) {
      long input = Integer.parseInt(br.readLine());
      if (input > 0) positive.add(input);
      else if (input < 0) negative.add(input);
      else zero++;
    }
    
  }  
}
