import java.io.*;
import java.util.*;

public class BOJ10825 {
  private static class Student implements Comparable<Student> {
    String name;
    int korean;
    int english;
    int math;
    Student(String name, int korean, int english, int math) {
      this.name = name;
      this.korean = korean;
      this.english = english;
      this.math = math;
    }
    @Override
    public int compareTo(Student other) {
      int filter1 = Integer.compare(other.korean, this.korean);
      if (filter1 == 0) {
        int filter2 = Integer.compare(this.english, other.english);
        if (filter2 == 0) {
          int filter3 = Integer.compare(other.math, this.math);
          if (filter3 == 0) {
            return this.name.compareTo(other.name);
          } else {
            return filter3;
          }
        } else {
          return filter2;
        }
      } else {
        return filter1;
      }
    } 
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int num = Integer.parseInt(br.readLine());
    Student[] data = new Student[num];
    for (int i = 0; i < num; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String name = st.nextToken();
      int korean = Integer.parseInt(st.nextToken());
      int english = Integer.parseInt(st.nextToken());
      int math = Integer.parseInt(st.nextToken());
      data[i] = new Student(name, korean, english, math);
    }
    Arrays.sort(data);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < num; i++) {
      sb.append(data[i].name).append('\n');
    }
    bw.write(sb.toString());
    bw.flush();
  }
}