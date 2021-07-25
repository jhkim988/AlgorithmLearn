import java.io.*;

public class BOJ5086 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String tmp[];

      tmp = br.readLine().split(" ");
      int first = Integer.parseInt(tmp[0]);
      int second = Integer.parseInt(tmp[1]);

      while (first != 0 && second != 0) {
        if (second % first == 0) {
          bw.write("factor\n");
        } else if (first % second == 0) {
          bw.write("multiple\n");
        } else {
          bw.write("neither\n");
        }
      
        tmp = br.readLine().split(" ");
        first = Integer.parseInt(tmp[0]);
        second = Integer.parseInt(tmp[1]);
      }
      bw.flush();
    } catch (IOException e) {

    }
  }
}
