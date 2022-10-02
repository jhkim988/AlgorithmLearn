import java.io.*;
import java.util.*;

public class BOJ2637 {
    private static class Pair {
        int end, weight;
        Pair(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        // Graph:
        ArrayList<Queue<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int composite = Integer.parseInt(st.nextToken());
            int base = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            graph.get(composite).add(new Pair(base, num));
        }

        ArrayList<Integer> baseParts = new ArrayList<>();
        boolean[] visit = new boolean[n+1];
        Stack<Integer> topologicalSort = new Stack<>();
        dfs(n, graph, visit, topologicalSort);

        int[] needs = new int[n+1];
        needs[n] = 1;
        while (!topologicalSort.isEmpty()) {
            int crnt = topologicalSort.pop();
            for (Pair adj : graph.get(crnt)) {
                needs[adj.end] += needs[crnt] * adj.weight;
            }
            if (graph.get(crnt).size() == 0) {
                baseParts.add(crnt);
            }
        } 
        Collections.sort(baseParts);
        for (int idx : baseParts) {
            bw.write(Integer.toString(idx));
            bw.write(' ');
            bw.write(Integer.toString(needs[idx]));
            bw.newLine();
        }
        bw.flush();
    }
    static void dfs(int x, ArrayList<Queue<Pair>> graph, boolean[] visit, Stack<Integer> stk) {
        visit[x] = true;
        for (Pair adj : graph.get(x)) {
            if (visit[adj.end]) continue;
            visit[adj.end] = true;
            dfs(adj.end, graph, visit, stk);
        }
        stk.push(x);
    }
}