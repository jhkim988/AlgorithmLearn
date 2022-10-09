import java.io.*;
import java.util.*;

public class BOJ1826 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] gasStation = new int[1_000_001];
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int gas = Integer.parseInt(st.nextToken());
            gasStation[pos] = gas;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int target = Integer.parseInt(st.nextToken());
        int gas = Integer.parseInt(st.nextToken());

        bw.write(Integer.toString(solution(gasStation, target, gas)));
        bw.flush();
    }
    private static int solution(int[] gasStation, int target, int gas) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int num = 0;
        for (int i = 0; i < target; i++) {
            if (gasStation[i] != 0) pq.add(gasStation[i]);
            if (gas == 0) {
                if (pq.isEmpty()) return -1;
                gas += pq.poll();
                num++;
            }
            gas--;
        }
        return num;
    }
}
