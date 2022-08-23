import java.io.*;
import java.util.*;

public class BOJ10800 {
  private static class Pair {
    int color;
    long size;
    Pair(int color, long size) {
      this.color = color;
      this.size = size;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Pair> arr = new ArrayList<>();
    ArrayList<Long> total = new ArrayList<>();
    HashMap<Integer, ArrayList<Long>> colorArray = new HashMap<>();
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int color = Integer.parseInt(st.nextToken());
      long size = Long.parseLong(st.nextToken());
      arr.add(new Pair(color, size));
      total.add(size);
      if (colorArray.containsKey(color)) {
        colorArray.get(color).add(size);
      } else {
        ArrayList<Long> addArray = new ArrayList<>();
        addArray.add(size);
        colorArray.put(color, addArray);
      }
    }
    Collections.sort(total);
    for (ArrayList<Long> color : colorArray.values()) {
      Collections.sort(color);
    }

    ArrayList<Long> psumTotal = new ArrayList<>();
    HashMap<Integer, ArrayList<Long>> psumColorArray = new HashMap<>();
    psumTotal.add(total.get(0));
    for (int i = 1; i < total.size(); i++) {
      psumTotal.add(psumTotal.get(i-1)+total.get(i));
    }
    for (Map.Entry<Integer, ArrayList<Long>> entry : colorArray.entrySet()) {
      int color = entry.getKey();
      ArrayList<Long> cArr = entry.getValue();
      ArrayList<Long> psumColor = new ArrayList<>();
      psumColor.add(cArr.get(0));
      for (int i = 1; i < cArr.size(); i++) {
        psumColor.add(psumColor.get(i-1)+cArr.get(i));
      }
      psumColorArray.put(color, psumColor);
    }

    for (Pair x : arr) {
      int totalIdx = binarySearch(total, x.size);
      int colorIdx = binarySearch(colorArray.get(x.color), x.size);
      long totalSum = totalIdx < 0 ? 0 : psumTotal.get(totalIdx);
      long colorSum = colorIdx < 0 ? 0 : psumColorArray.get(x.color).get(colorIdx);
      bw.write(Long.toString(totalSum - colorSum));
      bw.newLine();
    }
    bw.flush();
  }
  static int binarySearch(ArrayList<Long> arr, long key) {
    int lo = -1, hi = arr.size();
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (arr.get(mid) < key) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
}