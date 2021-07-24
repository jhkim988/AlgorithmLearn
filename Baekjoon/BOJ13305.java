import java.io.*;

public class BOJ13305 {
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int numCity = Integer.parseInt(br.readLine());
      long[] road = new long[numCity - 1];
      long[] city = new long[numCity];

      String[] tmp = br.readLine().split(" ");
      for (int i = 0; i < road.length; i++) {
        road[i] = Long.parseLong(tmp[i]);
      }
      tmp = br.readLine().split(" ");
      for (int i = 0; i < numCity; i++) {
        city[i] = Long.parseLong(tmp[i]);
      }

      int ptr = 0;
      long price = city[0];
      long sum = 0;
      while (ptr < numCity - 1) {
        sum += price * road[ptr];
        ptr++;
        if (city[ptr] < price) {
          price = city[ptr];
        }
      }
      bw.write(sum + "\n");
      bw.flush();
    } catch (IOException e) {

    }
  }
}
