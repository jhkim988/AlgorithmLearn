import java.io.*;

public class BOJ11729 {
  static int counter = 0;
  static OutputStreamWriter osw = new OutputStreamWriter(System.out);
  static BufferedWriter bw = new BufferedWriter(osw);

  public static void main(String[] args) {
    try {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      

      int numDisk = Integer.parseInt(br.readLine());
      br.close();
      isr.close();

      bw.write((pow(2, numDisk) - 1) + "\n");
      hanoi(numDisk, 1, 3);
      bw.flush();
      bw.close();
      osw.close();

    } catch (IOException e) {
      // do nothing
    }
  }

  static void hanoi(int numDisk, int start, int end) throws IOException{
    if (numDisk == 1) {
      counter++;
      bw.write(start + " " + end + "\n");
      return;
    }
    // start, end, another = 6 - start - end
    hanoi(numDisk - 1, start, 6 - start - end);
    hanoi(1, start, end);
    hanoi(numDisk - 1, 6 - start - end, end);
  }

  static int pow(int base, int exp) {
    int result = 1;
    for (int i = 0; i < exp; i++) {
      result *= base;
    }
    return result;
  }
}
