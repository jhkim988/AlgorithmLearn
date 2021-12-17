import java.io.*;
import java.util.*;

public class Run {
  public static void main(String[] args) throws IOException, InterruptedException {
    while (true) {
      ArrayList<String> c1 = new ArrayList<>();
      c1.add("java");
      c1.add("./InputMaker.java");
      ArrayList<String> c2 = new ArrayList<>();
      c2.add("java");
      c2.add("./BOJ22289.java");
      ArrayList<String> c3 = new ArrayList<>();
      c3.add("python");
      c3.add("./BOJ22289_productTest.py");
      ArrayList<String> c4 = new ArrayList<>();
      c4.add("java");
      c4.add("./OutputChecker.java");

      ProcessBuilder pb1 = new ProcessBuilder(c1);
      pb1.directory(new File("C:\\Users\\pc4\\Desktop\\BOJ22289"));
      Process p1 = pb1.start();
      p1.waitFor();
      
      ProcessBuilder pb2 = new ProcessBuilder(c2);
      pb2.directory(new File("C:\\Users\\pc4\\Desktop\\BOJ22289"));
      Process p2 = pb2.start();
      p2.waitFor();

      ProcessBuilder pb3 = new ProcessBuilder(c3);
      pb3.directory(new File("C:\\Users\\pc4\\Desktop\\BOJ22289"));
      Process p3 = pb3.start();
      p3.waitFor();

      File j = new File("./outputJava.txt");
      File p = new File("./outputPython.txt");

      FileReader jr = new FileReader(j);
      FileReader pr = new FileReader(p);

      char jc = '0', pc = '0';
      while (jc != -1 && pc != -1) {
        jc = (char) jr.read();
        pc = (char) pr.read();
        if (jc < '0' || jc > '9' || pc < '0' || pc > '9') break;
        if (jc != pc) {
          return;
        }
      }
      System.out.println("Same");
    }
  }
}
