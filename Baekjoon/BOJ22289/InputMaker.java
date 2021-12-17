import java.io.*;

public class InputMaker {
  public static void main(String[] args) throws IOException {
    File input = new File("./input.txt");
    FileWriter fw = new FileWriter(input);
    BufferedWriter bw = new BufferedWriter(fw);
    StringBuilder sb = new StringBuilder();
    int len = (int) (Math.random() * 900_000) + 100_000;
    sb.append((int) (Math.random() * 9) + 1);
    for (int i = 0; i < len; i++) {
      int r = (int) (Math.random() * 10);
      sb.append(r);
    }
    sb.append('\n');
    sb.append((int) (Math.random() * 9) + 1);
    for (int i = 0; i < len; i++) {
      int r = (int) (Math.random() * 10);
      sb.append(r);
    }
    bw.write(sb.toString());
    bw.close();
  }
}