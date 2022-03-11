import java.io.*;
import java.util.*;

public class BOJ2503 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= 9; j++) {
        if (i == j) continue;
        for (int k = 1; k <= 9; k++) {
          if (i == k || j == k) continue;
          
        }
      }
    }
  }
}
