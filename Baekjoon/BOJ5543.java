import java.io.*;

public class BOJ5543 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int bugger = 2000;
    int drink = 2000;
    for (int i = 0; i < 3; i++) {
      int input = Integer.parseInt(br.readLine());
      if (input < bugger) bugger = input;
    }
    for (int i = 0; i < 2; i++) {
      int input = Integer.parseInt(br.readLine());
      if (input <  drink) drink = input;
    }
    bw.write(Integer.toString(bugger + drink - 50));
    bw.newLine();
    bw.flush();
  }  
}
