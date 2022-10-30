import java.io.*;
import java.util.*;

public class BOJ5214 {
    private static int n, m;
    private static List<HashSet<Integer>> stationToGroupId, groupIdToStation;
    private static class Pair {
        int end, count;
        Pair(int end, int count) {
            this.end = end;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        stationToGroupId = new ArrayList<>();
        groupIdToStation = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            stationToGroupId.add(new HashSet<>());
        }
        for (int i = 0; i <= m; i++) {
            groupIdToStation.add(new HashSet<>());
        }

        for (int id = 1; id <= m; id++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                int station = Integer.parseInt(st.nextToken());
                stationToGroupId.get(station).add(id);
                groupIdToStation.get(id).add(station);
            }
        }

        int count = bfs();
        bw.write(Integer.toString(count));
        bw.flush();
    }
    private static int bfs() {
        if (n == 1) return 1;
        boolean[] visitGroup = new boolean[m+1];
        boolean[] visitNode = new boolean[n+1];
        Queue<Pair> que = new LinkedList<>();
        for (int id : stationToGroupId.get(1)) {
            que.add(new Pair(id, 1));
            visitGroup[id] = true;
        }
        visitNode[1] = true;
    
        while (!que.isEmpty()) {
            Pair crnt = que.poll();
            for (int station : groupIdToStation.get(crnt.end)) {
                if (visitNode[station]) continue;
                if (station == n) return crnt.count+1;
                visitNode[station] = true;
                for (int id : stationToGroupId.get(station)) {
                    if (visitGroup[id]) continue;
                    visitGroup[id] = true;
                    que.add(new Pair(id, crnt.count+1));
                }
            }
        }
        return -1;
    }
}
