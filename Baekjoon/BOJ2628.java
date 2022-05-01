import java.io.*;
import java.util.*;

public class BOJ2628 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int wid = Integer.parseInt(st.nextToken());
    int hei = Integer.parseInt(st.nextToken());
    ArrayList<Integer> rows = new ArrayList<>();
    ArrayList<Integer> cols = new ArrayList<>();
    rows.add(0);
    cols.add(0);
    rows.add(hei);
    cols.add(wid);
    int nInput = Integer.parseInt(br.readLine());
    while (nInput-- > 0) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int input = Integer.parseInt(st.nextToken());
      if (type == 0) {
        rows.add(input);
      } else {
        cols.add(input);
      }
    }
    Collections.sort(rows);
    Collections.sort(cols);
    int maxRow = 0;
    for (int i = 1; i < rows.size(); i++) {
      if (maxRow < rows.get(i)-rows.get(i-1)) {
        maxRow = rows.get(i)-rows.get(i-1);
      }
    }
    int maxCol = 0;
    for (int i = 1; i < cols.size(); i++) {
      if (maxCol < cols.get(i)-cols.get(i-1)) {
        maxCol = cols.get(i)-cols.get(i-1);
      }
    }
    bw.write(Integer.toString(maxRow * maxCol));
    bw.flush();
  }
}
