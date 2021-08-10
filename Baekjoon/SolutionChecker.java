import java.io.*;
import java.util.*;

public class SolutionChecker {
  public static void main(String[] args) throws IOException {
    File dir = new File("./AlgorithmLearn/Baekjoon");
    File files[] = dir.listFiles();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    System.out.println("-------------------------------");
    String[] tmp = new String[240];
    for (int i = 0; i < 240; i++) {
      tmp[i] = st.nextToken();
    }
    for (File f : files) {
      boolean flag = false;
      for (int i = 0; i < 240; i++) {
        if (f.toString().contains(tmp[i])) {
          flag = true;
        }
      }
      if (!flag) {
        System.out.println(f);
      }      
    }
  }
}
