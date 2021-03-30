import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch {
	// Depth-First-Search
	// e.g. Maze exploration
	// Maze exploration - Tremaux maze exploration Algorithm
	// Goal - Systemically Search through a graph.
	// Idea - Mimic maze exploration

//	DFS(to visit a vertex v) {
//		mark v as visited -> use mark array(boolean[])
//		recursively visit all unmarked vertices w adjacent to v.
//	}
	
	// proposition
	// DFS marks all vertices connected to s
	// in time propotional to the "sum of their degrees".
	
	// After DFS, can find vertices connected to s in constant time
	// and can find a path to s (if one exists) in the proportional to its length.
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public DepthFirstSearch(Graph G, int s) {
		dfs(G, s);
	}
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v))
			if (!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
	}
}

class Paths {
	// decouple the graph representation from process it.
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	Paths(Graph G, int s) {
		// find paths in G from source s
		dfs(G, s);
	}
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v))
			if (!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
	}
	boolean hasPathTo(int v) {
		// is there a path from s to v?
		return marked[v];
	}
	Iterable<Integer> pathTo(int v) {
		// path from s to v: null if no such path
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
	public static void main(String[] args) {
		Graph G = new Graph(10);
		int s = 3;
		Paths paths = new Paths(G, s);
		for(int v = 0; v < G.V(); v++) // print all vertices connected to s
			if (paths.hasPathTo(v))
				StdOut.println(v);
	}
}
