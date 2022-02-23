package Test;
import java.io.*;

public class Check {
  public static void main(String[] args) throws IOException {
    int num = 1;
    for (int id = 1; id <= num; id++) {
      BufferedReader test = new BufferedReader(new FileReader(new File("./Test/output.txt")));
      BufferedReader solution = new BufferedReader(new FileReader(new File("./Test/ts1_output.txt")));
      String t = test.readLine();
      String s = solution.readLine();
      int line = 0;
      while (s != null) {
        if (!t.equals(s)) {
          System.out.println("wrong: ");
          System.out.println("id: " + id);
          System.out.println("line: " + line);
          test.close();
          solution.close();
          return;
        }
        t = test.readLine();
        s = solution.readLine();
        line++;
      }
      test.close();
      solution.close();
    }
    System.out.println("correct");
  } 
}
