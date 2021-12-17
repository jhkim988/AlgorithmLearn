package BOJ22289;

import java.io.*;

public class OutputChecker {
  public static void main(String[] args) throws IOException {
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
        System.out.println("Wrong!!!");
        return;
      }
    }    
    System.out.println("SAME");
  }
}
