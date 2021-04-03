import edu.princeton.cs.algs4.Stack;

public class StrongComponents {
	// Strong connected: Vertices v and w are strong connected
	// if there is a directed path from v to w and a direct path from w to v.
	// Strong connectivity is an equivalence relation.
	// Strong components: equivalence class.
	// (In DAG, # of element for each Strong components = 1 i.e. itself)
	
	// connected components vs strongly connected components
	
	// Application
	// ecological food webs
	// software modules
	
	// Kosaraju-Sharir Algorithm:
	// Reverse Graph. Strong components in G are same as in G^R(reverse graph of G).
	// kernel DAG. Contract each strong component into a single vertex.
	// idea.
	// Compute topological order(reverse postorder) in kernel DAG.
	// Run DFS, considering vertices in reverse topological order.
	
	// 1. DFS in reverse graph. Compute reverse postorder in G^R
	// 2. DFS in original graph with computed above order(reverse postorder in G^R)
	
	private boolean[] marked;
	private int[] id;
	private int count;
	
	private class DepthFirstOrder {
		private boolean[] marked;
		private Stack<Integer> stk;
		
		DepthFirstOrder(Digraph G) {
			marked = new boolean[G.V()];
			stk = new Stack<Integer>();
			
			for (int v = 0; v < G.V() && !marked[v]; v++)
				dfs(G, v);
		}
		private void dfs(Digraph G, int v) {
			marked[v] = true;
			for(int w : G.adj(v))
				if (!marked[w]) dfs(G, w);
			stk.push(v);
		}
		Iterable<Integer> reversePost() {
			return stk;
		}
	}
	
	StrongComponents(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());
		for (int v : dfs.reversePost()) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v))
			if (!marked[w]) dfs(G, w);
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
}
