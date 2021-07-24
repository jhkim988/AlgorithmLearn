import java.io.*;

public class BOJ1541 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String tmp = br.readLine();
      String[] tmpNum = tmp.split("[^0-9]");
      String[] oper = tmp.split("[0-9]+");
      int len = tmpNum.length;
      int[] numData = new int[len];
      for (int i = 0; i < len; i++) {
        numData[i] = Integer.parseInt(tmpNum[i]);
      }

      int ptrNum = 1;
      int ptrOper = 1;
      int calc = numData[0];

      while (ptrNum < len && ptrOper < len) {
        if (oper[ptrOper].charAt(0) == '-') {
          calc -= numData[ptrNum];
          // System.out.println("- " + numData[ptrNum]);
          while (ptrOper + 1 < len && oper[ptrOper + 1].charAt(0) == '+') {
            ptrOper++;
            ptrNum++;
            calc -= numData[ptrNum];
            // System.out.println("- " + numData[ptrNum]);
          }
        } else {
          // System.out.println("+ " + numData[ptrNum]);
          calc += numData[ptrNum];
        }
        ptrNum++;
        ptrOper++;
      }
      bw.write(calc + "\n");
      bw.flush();
    } catch (IOException e) {

    }
  }
}
