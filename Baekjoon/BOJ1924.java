import java.io.*;
import java.util.*;

public class BOJ1924 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int m = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());
    int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int accDay = d;
    for (int i = 0; i < m-1; i++) {
      accDay += days[i];
    }
    String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    bw.write(week[accDay%7]);
    bw.newLine();
    bw.flush();
  } 
}