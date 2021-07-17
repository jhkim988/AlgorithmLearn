import java.io.*;

public class BOJ1018 {
  public static void main(String[] args) {
    // Caution, check rule
    try {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      OutputStreamWriter osw = new OutputStreamWriter(System.out);
      BufferedWriter bw = new BufferedWriter(osw);

      String[] tmp = br.readLine().split(" ");

      // width >= 8, height >= 8
      int height = Integer.parseInt(tmp[0]);
      int width = Integer.parseInt(tmp[1]);

      String[] data = new String[height];
      for(int i = 0; i < height; i++) {
        data[i] = br.readLine();
      }
      br.close();
      isr.close();

      int minDraw = 65;
      int count;
      for (int i = 0; i <= width - 8; i++) {
        for (int j = 0; j <= height - 8; j++) {
          count = countDraw(data, i, j);
          if (count < minDraw) {
            minDraw =  count;
          } 
        }
      }
      bw.write(minDraw + "\n");
      bw.flush();
      bw.close();
      osw.close();
  } catch (IOException e) {
    // do nothing
  }
}
  static int countDraw(String[] data, int hori, int verti) {
    int count1 = 0;
    int count2 = 0;
    boolean controlSwitch;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        controlSwitch = (i + j) % 2 == 0;
        if (controlSwitch) {
          if ((data[i + verti].charAt(j + hori) != 'W')) {
            count1++;
          }
        } else {
          if ((data[i + verti].charAt(j + hori) != 'B')) {
            count1++;
          }
        }

      }
    }
     
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        controlSwitch = (i + j) % 2 == 0;
        if (controlSwitch) {
          if ((data[i + verti].charAt(j + hori) != 'B')) {
            count2++;
          }
        } else {
          if ((data[i + verti].charAt(j + hori) != 'W')) {
            count2++;
          }
        }
      }
    }
    return Math.min(count1, count2);
  }
}
