import java.util.*;

public class ArticulationPoint {
	private static Graph graph;
	private static int id = 0;
	private static int[] dfsId;
	private static boolean[] isAP;

	static ArrayList<Integer> aplist() {
		ArrayList<Integer> list = new ArrayList<>();
		dfsId = new int[graph.size()];
		for (int i = 1; i <= graph.size(); i++) {
			if (dfsId[i] == 0)
				dfs(i, true);
		}
		for (int i = 1; i <= graph.size(); i++) {
			if (isAP[i])
				list.add(i);
		}
		return list;
	}

	static int dfs(int node, boolean isRoot) {
		int min = id, numChild = 0;
		dfsId[node] = id++;
		for (int adj : graph.get(node)) {
			if (dfsId[adj] == 0) { // 자손이라면
				numChild++;
				int low = dfs(adj, false); // adj와 연결된 가장 작은 dfsId
				if (!isRoot && low >= dfsId[node])
					isAP[node] = true; // node의 선조와 adj가 연결돼 있지 않는다면
				min = Integer.min(min, low);
			} else {
				min = Integer.min(min, dfsId[adj]);
			}
		}
		// Root에 자손이 2개 이상 있으면 단절점이다.
		if (isRoot)
			isAP[node] = numChild >= 2;
		return min;
	}

	public static void main(String[] args) {

	}
}
