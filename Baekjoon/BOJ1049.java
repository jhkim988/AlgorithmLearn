import java.io.*;
import java.util.*;

public class BOJ1049 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int needWire = Integer.parseInt(st.nextToken());
    int numBrand = Integer.parseInt(st.nextToken());
    int minSetPrice = Integer.MAX_VALUE;
    int minSinglePrice = Integer.MAX_VALUE;
    for (int i = 0; i < numBrand; i++) {
      st = new StringTokenizer(br.readLine());
      int setPrice = Integer.parseInt(st.nextToken());
      int singlePrice = Integer.parseInt(st.nextToken());
      if (setPrice < minSetPrice) minSetPrice = setPrice;
      if (singlePrice < minSinglePrice) minSinglePrice = singlePrice;
    }

    int allSet = (needWire / 6 + (needWire % 6 == 0 ? 0 : 1)) * minSetPrice;
    int allSingle = needWire * minSinglePrice;
    int singleRemainder = (needWire / 6) * minSetPrice + (needWire % 6) * minSinglePrice;
    int min = Integer.min(allSet, Integer.min(allSingle, singleRemainder));

    bw.write(Integer.toString(min));
    bw.newLine();
    bw.flush();
  }
}
