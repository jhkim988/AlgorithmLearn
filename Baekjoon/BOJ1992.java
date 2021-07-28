import java.io.*;

public class BOJ1992 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    int[][] data = new int[N][N];
    char[] tmp;
    for (int i = 0; i < N; i++) {
      tmp = br.readLine().toCharArray();
      for (int j = 0; j < N; j++) {
        data[i][j] = Integer.parseInt(tmp[j] + "");
      }
    } 
    bw.write(comp(data));
    bw.flush();
  } 
  static String comp(int[][] data) {
    return comp(data, 0, data.length, 0, data.length);
  }
  static String comp(int[][] data, int widStart, int widEnd, int heiStart, int heiEnd) {
    int code = data[widStart][heiStart];
    boolean flag = true;
    for (int i = widStart; i < widEnd; i++) {
      for (int j = heiStart; j < heiEnd; j++) {
        if (data[i][j] != code) {
          flag = false;
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    if (!flag) {
      sb.append("(");
      sb.append(comp(data, widStart, (widStart + widEnd) / 2, heiStart, (heiStart + heiEnd) / 2));      
      sb.append(comp(data, widStart, (widStart + widEnd) / 2, (heiStart + heiEnd) / 2, heiEnd));
      sb.append(comp(data, (widStart + widEnd) / 2, widEnd, heiStart, (heiStart + heiEnd) / 2));
      sb.append(comp(data, (widStart + widEnd) / 2, widEnd, (heiStart + heiEnd) / 2, heiEnd));
      sb.append(")");
      return sb.toString();
    } else {
      sb.append(code == 0 ? "0" : "1");
    } 
    return sb.toString();
  }
}
