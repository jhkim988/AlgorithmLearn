import java.io.*;
import java.util.Arrays;

public class BOJ1931 {
  private static class Meeting implements Comparable<Meeting> {
    long start;
    long end;
    Meeting(long start, long end) {
      this.start = start;
      this.end = end;
    }
    @Override
    public int compareTo(Meeting that) {
      int result = Long.compare(this.end, that.end);
      return result == 0 ? Long.compare(this.start, that.start) : result;
    }
  }
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    try {
      int num = Integer.parseInt(br.readLine());
      String[] tmp;
      Meeting[] data = new Meeting[num];
      for (int i = 0; i < num; i++) {
        tmp = br.readLine().split(" ");
        data[i] = new Meeting(Long.parseLong(tmp[0]), Long.parseLong(tmp[1]));
      }
      Arrays.sort(data);

      int count = 0;
      int ptr = 0;

      while (ptr < num) {
        count++;
        int i = 1;
        while (ptr + i < num && data[ptr].end > data[ptr + i].start) {
          i++;
        }
        ptr += i;
      }

      bw.write(count + "\n");
      bw.flush();
    } catch (IOException e) { 
      // do nothing
    }
  }
}
