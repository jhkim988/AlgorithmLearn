import java.io.*;
import java.util.*;

public class BOJ2617 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // graph initilize:
        ArrayList<HashSet<Integer>> graph = new ArrayList<>();
        ArrayList<HashSet<Integer>> reverse = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new HashSet<>());
            reverse.add(new HashSet<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());
            graph.get(light).add(heavy);
            reverse.get(heavy).add(light);
        }
        
        int[] numHeavy = new int[n+1];
        int[] numLight = new int[n+1];
        Arrays.fill(numHeavy, -1);
        Arrays.fill(numLight, -1);

        // dfs
        boolean[] visit = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visit, false);
            numHeavy[i] = dfs(i, graph, visit);
        }
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visit, false);
            numLight[i] = dfs(i, reverse, visit);
        }
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (numHeavy[i] >= (n+1)/2 || numLight[i] >= (n+1)/2) {
                answer++;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
    static int dfs(int x, ArrayList<HashSet<Integer>> graph, boolean[] visit) {
        visit[x] = true;
        int num = 0;
        for (int adj: graph.get(x)) {
            if (visit[adj]) continue;
            num += dfs(adj, graph, visit)+1;
        }
        return num;
    }
}