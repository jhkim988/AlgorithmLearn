import java.io.*;

public class BOJ2447 {
  public static void main(String[] args) {
    try {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      OutputStreamWriter osw = new OutputStreamWriter(System.out);
      BufferedWriter bw = new BufferedWriter(osw);

      int input = Integer.parseInt(br.readLine());
      br.close();
      isr.close();

      String[] result = starPrint(input);
      for (int i = 0; i < result.length; i++) {
        bw.write(result[i] + "\n");
      }
      bw.flush();
      bw.close();
      osw.close();

    } catch (IOException e) {
      // do nothing
    }
  }
  static String blank(int num) {
    // num blank
    String result = "";
    for (int i = 0; i < num; i++) {
        result += " ";  
    }
    return result;
  }

  static String[] starPrint(int num) { // input
    String[] result = new String[num];
    if (num == 3) {
      result[0] = "***";
      result[1] = "* *";
      result[2] = "***";
      return result;
    }
    String[] prev = starPrint(num / 3);
    for (int i = 0; i < num/3; i++) {
      result[i] = prev[i] + prev[i] + prev[i];
    }
    for (int i = 0; i < num/3; i++) {
      result[i + num/3] = prev[i] + blank(num / 3) + prev[i];
    }
    for (int i = 0; i < num/3; i++) {
      result[i + 2*num/3] = prev[i] + prev[i] + prev[i];
    }

    return result;
  }
}
