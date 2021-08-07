import java.io.*;
import java.util.*;

public class BOJ7662 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int numOper = Integer.parseInt(br.readLine());
      TreeMap<Integer, Integer> minTree = new TreeMap<>();
      
      while (numOper-- > 0) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String oper = st.nextToken();
        if (oper.equals("I")) {
          int input = Integer.parseInt(st.nextToken());
          if (minTree.containsKey(input)) {
            minTree.put(input, minTree.get(input) + 1);
          } else {
            minTree.put(input, 0);
          }          
        } else if (oper.equals("D")) {
          if (minTree.isEmpty()) {
            continue;
          }
          int input = Integer.parseInt(st.nextToken());
          if (input == 1) {
            int max = minTree.lastKey();
            int count = minTree.get(max);
            if (count > 0) {
              minTree.put(max, count - 1);
            } else {
              minTree.remove(max);
            }            
          } else {
            int min = minTree.firstKey();
            int count = minTree.get(min);
            if (count > 0) {
              minTree.put(min, count - 1);
            } else {
              minTree.remove(min);
            }
          }
        } 
      }
      if (minTree.isEmpty()) {
        bw.write("EMPTY\n");
      } else {
        bw.write(minTree.lastKey() + " " + minTree.firstKey() + "\n");
      }
    }
    bw.flush();
  }  
}
