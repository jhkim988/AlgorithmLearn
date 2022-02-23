package Test;
import java.io.*;
public class MakeInput {
  public static void main(String[] args) throws IOException {
    for (int id = 1; id <= 10; id++) {
      String cmd = "java ./Test/Test.java < ./Test/alloc.in." + id + " > ./Test/test_" + id + ".txt";
      System.out.println(cmd);
      Runtime.getRuntime().exec("cmd /c" + cmd);
    }
  }
}
