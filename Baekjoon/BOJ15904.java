import java.io.*;
import java.util.*;

public class BOJ15904 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    ArrayList<Integer> input = new ArrayList<>();
    int ch;
    while ((ch = br.read()) != -1) {
      if (ch == 'U' || ch == 'C' || ch == 'P') input.add(ch);
    }
    if (possible(input)) bw.write("I love UCPC");
    else bw.write("I hate UCPC");
    bw.flush();
  }
  static boolean possible(ArrayList<Integer> input) {
    char[] filter = {'U', 'C', 'P', 'C'};
    int ptr = 0;
    for (int i = 0; i < 4; i++) {
      for (; ptr < input.size(); ptr++) {
        if (input.get(ptr) == filter[i]) break;
      }
      if (ptr == input.size()) return false;
      ptr++;
    }
    return true;
  }
}
