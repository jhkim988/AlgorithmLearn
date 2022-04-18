import java.io.*;
import java.util.*;

public class BOJ5635 {
  private static class Student {
    String name;
    int y, m, d;
    Student(String name, int d, int m, int y) {
      this.name = name;
      this.y = y;
      this.m = m;
      this.d = d;
    }
  } 
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    Student[] arr = new Student[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      arr[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    Arrays.sort(arr, (a, b) -> {
      if (a.y != b.y) return a.y-b.y;
      if (a.m != b.m) return a.m-b.m;
      if (a.d != b.d) return a.d-b.d;
      return a.name.compareTo(b.name);
    });
    bw.write(arr[arr.length-1].name);
    bw.newLine();
    bw.write(arr[0].name);
    bw.flush();
  } 
}
