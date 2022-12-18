import java.io.*;
import java.util.*;

public class BOJ14469 {
    private static class Pair {
        int arrival, interval;
        Pair(int arrival, int interval) {
            this.arrival = arrival;
            this.interval = interval;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Pair[] schedule = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int arrival = Integer.parseInt(st.nextToken());
            int interval = Integer.parseInt(st.nextToken());
            schedule[i] = new Pair(arrival, interval);
        }
        Arrays.sort(schedule, (a, b) -> {
            if (a.arrival == b.arrival) return Integer.compare(b.interval, a.interval);
            return Integer.compare(a.arrival, b.arrival);
        });

        long time = 0;
        for (int i = 0; i < n; i++) {
            if (time < schedule[i].arrival) {
                time = schedule[i].arrival + schedule[i].interval;
            }  else {
                time += schedule[i].interval;
            }
        }

        bw.write(Long.toString(time));
        bw.flush();
    }
}
