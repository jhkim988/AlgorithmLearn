import java.io.*;

public class BOJ12865 {
  private static class Item {
    int weight;
    int value;
    Item (int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
  }
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] tmp = br.readLine().split(" ");
      int numItem = Integer.parseInt(tmp[0]);
      int limitWeight = Integer.parseInt(tmp[1]);

      Item[] data = new Item[numItem]; // data[][] = [weight, value]
      for (int i = 0; i < numItem; i++) {
        tmp = br.readLine().split(" ");
        data[i] = new Item(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
      }

      int[][] table = new int[numItem][limitWeight + 1];

      for (int j = 0; j <= limitWeight; j++) {
        if (data[0].weight <= j) {
          table[0][j] =  data[0].value;
        }
      }

      for (int i = 1; i < numItem; i++) {
        for (int j = 0; j <= limitWeight; j++) {
          if (data[i].weight > j) {
            table[i][j] = table[i - 1][j];
          } else {
            table[i][j] = Math.max(table[i - 1][j - data[i].weight] + data[i].value, table[i - 1][j]);
          }
        }
      }
      bw.write(table[numItem - 1][limitWeight] + "\n");
      bw.flush();
    } catch (IOException e) {
      // do nothing
    }
  }
}
