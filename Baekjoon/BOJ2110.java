import java.io.*;
import java.util.Arrays;

public class BOJ2110 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    int numHouse = Integer.parseInt(tmp[0]);
    int numRouter = Integer.parseInt(tmp[1]);

    int[] coordi = new int[numHouse];
    for (int i = 0; i < numHouse; i++) {
      coordi[i] = Integer.parseInt(br.readLine());
    }
    
    Arrays.sort(coordi);
    
    int minDist = 1;
    int maxDist = coordi[numHouse - 1] - coordi[0];
    int mid = 0;
    int sol = 0;
    while (minDist <= maxDist) {
      mid = (maxDist + minDist) / 2;
      if (checkSetRouter(mid, coordi, numRouter)) {
        minDist = mid + 1;
        sol = mid;
      } else {
        maxDist = mid - 1;  
      }
    }
    bw.write(sol + "\n");
    bw.flush();
  }
  static boolean checkSetRouter(int dist, int[] coordi, int numRouter) {
    int visit = 0;
    int setUp = 1; // set up at coordi[0]
    for (int i = 1; i < coordi.length; i++) {
      if (setUp < numRouter) {
        if (coordi[i] - coordi[visit] >= dist) {
          setUp++;
          visit = i;
        }        
      }
    }
    return numRouter <= setUp;
  }
  static void mySolution() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] tmp = br.readLine().split(" ");
    int numHouse = Integer.parseInt(tmp[0]);
    int numRouter = Integer.parseInt(tmp[1]);

    int[] coordi = new int[numHouse];
    for (int i = 0; i < numHouse; i++) {
      coordi[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(coordi);
    
    int minDist = 1;
    int maxDist = coordi[numHouse - 1] - coordi[0];
    int mid = 0;
    int sol = 0;
    while (minDist <= maxDist) {
      mid = (maxDist + minDist) / 2;
      int ptr = 0;
      int rtr = 1; // set up at coordi[0]
      while (coordi[ptr] + mid <= coordi[numHouse - 1]) {
        int idx = Arrays.binarySearch(coordi, coordi[ptr] + mid);
        idx = idx < 0 ? -(idx + 1) : idx;
        rtr++;
        ptr = idx;
      }
      if (rtr >= numRouter) { 
        minDist = mid + 1;
        sol = mid;
      } else if (rtr < numRouter) {
        maxDist = mid - 1;
      }
    }
    bw.write(sol + "\n");
    bw.flush();
  }
}
