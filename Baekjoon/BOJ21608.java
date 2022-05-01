import java.io.*;
import java.util.*;

public class BOJ21608 {
  private static class Student {
    int id;
    ArrayList<Integer> love;
    Student(int id) {
      this.id = id;
      love = new ArrayList<>();
    }
    void add(int other) {
      love.add(other);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    
  }
}
