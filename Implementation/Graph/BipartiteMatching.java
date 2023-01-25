import java.io.*;
import java.util.*;

public class BipartiteMatching {
	// Group1: [1, 2, 3, ..., n];
	// Group2: [1, 2, 3, ..., m];
	// Group1 -> Group2 Matching
	int n, m;
	int[] group1, group2;
	ArrayList<Queue<Integer>> graph;
	boolean[] visit;

	BipartiteMatching(int n, int m) {
		this.n = n;
		this.m = m;
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new LinkedList<>());
		}
		group1 = new int[n + 1];
		group2 = new int[m + 1];
	}

	// Group1 a -> Group2 b
	void add(int a, int b) {
		graph.get(a).add(b);
	}

	int match() {
		int max = 0;
		visit = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			if (group1[i] == 0)
				continue;
			Arrays.fill(visit, false);
			if (dfs(i))
				max++;
		}
		return max;
	}

	boolean dfs(int a) {
		visit[a] = true;
		for (int b : graph.get(a)) {
			if (group2[b] == 0 || (!visit[group2[b]] && dfs(group2[b]))) {
				group1[a] = b;
				group2[b] = a;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {

	}
}
