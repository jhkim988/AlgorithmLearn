import java.io.*;
import java.util.*;

public class BOJ2875 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int girl = Integer.parseInt(st.nextToken());
    int boy = Integer.parseInt(st.nextToken());
    int slave = Integer.parseInt(st.nextToken());

    int maxTeam = boy <= girl / 2 ? boy : girl / 2;
    int remainder = girl + boy - maxTeam * 3;
    int answer = 0;
    if (slave <= remainder) {
      answer = maxTeam;
    } else {
      int add = (slave - remainder) % 3 == 0 ? 0 : 1;
      answer = maxTeam - (slave - remainder) / 3 - add;
    }

    bw.write(answer + "\n");
    bw.flush();
  }
}
