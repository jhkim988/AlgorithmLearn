import java.io.*;

public class BOJ1149 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int numHouse = Integer.parseInt(br.readLine());
            int[][] costData = new int[numHouse][3];
            String[] tmp;
            for (int i = 0; i < numHouse; i++) {
                tmp = br.readLine().split(" ");
                for (int j = 0; j < 3; j++) {
                    costData[i][j] = Integer.parseInt(tmp[j]);
                }
            }

            bw.write(minCost(costData)+"\n");
            bw.flush();
            bw.close();
            br.close();

        } catch (IOException e) {

        }
    }
    static int minCost(int[][] costData) {
        int len = costData.length;
        int[][] minSumCost = new int[len][3];
        
        // initialize
        for (int i = 0; i < 3; i++) {
            minSumCost[0][i] = costData[0][i];
        }
        
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                minSumCost[i][j] = minSumCost[i - 1][(j + 1) % 3] > minSumCost[i - 1][(j + 2) % 3]
                ? costData[i][j] + minSumCost[i - 1][(j + 2) % 3]
                : costData[i][j] + minSumCost[i - 1][(j + 1) % 3];
            }
        }
        len--;
        return minSumCost[len][0] > minSumCost[len][1]
        ? (minSumCost[len][1] > minSumCost[len][2] ? minSumCost[len][2] : minSumCost[len][1])
        : (minSumCost[len][0] > minSumCost[len][2] ? minSumCost[len][2] : minSumCost[len][0]);
    }
}
