import java.io.*;
import java.util.*;

public class BOJ1431 {
  private static class Serial implements Comparable<Serial> {
    char[] serial;
    int len;
    int sum;
    Serial(String str) {
      serial = str.toCharArray();
      len = serial.length;
      for (char ch : serial) {
        if ('0' <= ch && ch <= '9') {
          sum += ch - '0';
        }
      }
    }
    @Override
    public int compareTo(Serial other) {
      if (this.len != other.len) return this.len - other.len;
      if (this.sum != other.sum) return this.sum - other.sum;
      for (int i = 0; i < len; i++) {
        if (this.serial[i] != other.serial[i]) return this.serial[i] - other.serial[i];
      }
      return 0;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    ArrayList<Serial> al = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      al.add(new Serial(br.readLine()));
    }
    Collections.sort(al);
    for (Serial s : al) {
      bw.write(s.serial);
      bw.newLine();
    }
    bw.flush();
  }
}
