import java.io.*;
public class BOJ2798 {
  public static void main(String[] args) {
    try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] tmp = br.readLine().split(" ");
        int numCard = Integer.parseInt(tmp[0]);
        int sumLimit = Integer.parseInt(tmp[1]);

        tmp = br.readLine().split(" "); // length = numCard
        br.close();

        int[] cardList = new int[numCard];
        for (int i = 0; i < numCard; i++) {
          cardList[i] = Integer.parseInt(tmp[i]);
        }

        int sum;
        int max = 0;
        for (int i = 0; i < numCard; i++) {
          for (int j = i + 1; j < numCard; j++) {
            for (int k = j + 1; k < numCard; k++) {
              sum = cardList[i] + cardList[j] + cardList[k];
              if (sum <= sumLimit && max < sum) {
                max = sum;
              }
            }
          }
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();

    } catch (IOException e) {

    }
  }
}
