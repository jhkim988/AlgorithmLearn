import java.io.*;
import java.util.*;

public class BOJ1461 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    ArrayList<Integer> negative = new ArrayList<>();
    ArrayList<Integer> positive = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      int input = Integer.parseInt(st.nextToken());
      if (input < 0) negative.add(-input);
      else positive.add(input);
    }
    Collections.sort(negative);
    Collections.sort(positive);
    int answer = 0;
    int pptr = positive.size()-1;
    int nptr = negative.size()-1;

    if (positive.size() != 0 && negative.size() != 0) {
      if (positive.get(pptr) < negative.get(nptr)) {
        answer += negative.get(nptr);
        for (int i = 0; i < m && nptr >= 0; i++) nptr--;
      } else {
        answer += positive.get(pptr);
        for (int i = 0; i < m && pptr >= 0; i++) pptr--;
      }
      while (nptr >= 0) {
        answer += 2*negative.get(nptr);
        for (int i = 0; i < m && nptr >= 0; i++) nptr--;
      }
      while (pptr >= 0) {
        answer += 2*positive.get(pptr);
        for (int i = 0; i < m && pptr >= 0; i++) pptr--;
      }
    } else {
      if (positive.size() != 0) {
        answer += positive.get(pptr);
        for (int i = 0; i < m && pptr >= 0; i++) pptr--;
        while (pptr >= 0) {
          answer += 2*positive.get(pptr);
          for (int i = 0; i < m && pptr >= 0; i++) pptr--;
        }
      } else {
        answer += negative.get(nptr);
        for (int i = 0; i < m && nptr >= 0; i++) nptr--;
        while (nptr >= 0) {
          answer += 2*negative.get(nptr);
          for (int i = 0; i < m && nptr >= 0; i++) nptr--;
        }
      }
    }
    bw.write(Integer.toString(answer));
    bw.flush();
  }
}
