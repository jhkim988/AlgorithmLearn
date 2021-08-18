import java.io.*;
import java.util.*;

public class BOJ11723 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numOper = Integer.parseInt(br.readLine());
    int bit = 0b0000_0000_0000_0000_0000;
    StringBuilder sb = new StringBuilder();
    while (numOper-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String oper = st.nextToken();
      if (oper.equals("add")) {
        int input = Integer.parseInt(st.nextToken());
        bit = bit | (1 << input);
      } else if (oper.equals("remove")) {
        int input = Integer.parseInt(st.nextToken());
        bit = bit & ~(1 << input);
      } else if (oper.equals("check")) {
        int input = Integer.parseInt(st.nextToken());
        sb.append(((bit & (1 << input)) != 0) ? "1\n" : "0\n");
      } else if (oper.equals("toggle")) {
        int input = Integer.parseInt(st.nextToken());
        bit = bit ^ (1 << input);
      } else if (oper.equals("all")) {
        bit = 0b1111_1111_11111_1111_1111;
      } else if (oper.equals("empty")) {
        bit = 0b0000_0000_0000_0000_0000;
      } else {
        // Other operation. do nothing
      }
    }
    bw.write(sb.toString());
    bw.flush();
  }
  public static void useHashSet() throws IOException {    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int numOper = Integer.parseInt(br.readLine());
    HashSet<Integer> set = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    while (numOper-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String oper = st.nextToken();
      if (oper.equals("add")) {
        set.add(Integer.parseInt(st.nextToken()));
      } else if (oper.equals("remove")) {
        set.remove(Integer.parseInt(st.nextToken()));
      } else if (oper.equals("check")) {
        String result = set.contains(Integer.parseInt(st.nextToken())) ? "1\n" : "0\n";
        sb.append(result);
      } else if (oper.equals("toggle")) {
        int input = Integer.parseInt(st.nextToken());
        if (set.contains(input)) {
          set.remove(input);
        } else {
          set.add(input);
        }
      } else if (oper.equals("all")) {
        set = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
          set.add(i);
        }
      } else if (oper.equals("empty")) {
        set = new HashSet<>();
      } else {
        // Other operation. do nothing
      }
    }
    bw.write(sb.toString());
    bw.flush();
  }
}
