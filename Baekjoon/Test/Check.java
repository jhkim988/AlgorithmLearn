package Test;
import java.io.*;

public class Check {
  public static void main(String[] args) throws IOException {
    for (int id = 1; id <= 10; id++) {
      BufferedReader test = new BufferedReader(new FileReader(new File("./Test/test_" + id + ".txt")));
      BufferedReader solution = new BufferedReader(new FileReader(new File("./Test/alloc.out."+id)));
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
