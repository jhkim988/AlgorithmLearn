import java.util.*;

public class EulerTourTech {
	static int id = 1;
	static int[] start, end;
	static ArrayList<Queue<Integer>> tree;

	public static void main(String[] args) {
		// Tree에서 node i를 root로 하는 subtree에 대한 쿼리 처리
		// 각 node에 진입하는 시점, 나가는 시점을 기록하여 시점으로 구간 쿼리를 처리한다.
		int n = 10, root = 1;
		tree = new ArrayList<>();
		start = new int[n];
		end = new int[n];
		start[root] = id;
		dfs(root);

		// subtree of i:
		// query(start[i], end[i]);
	}

	static void dfs(int node) {
		for (int adj : tree.get(node)) {
			if (start[adj] != 0)
				continue;
			start[adj] = ++id;
			dfs(adj);
		}
		end[node] = id;
	}
}