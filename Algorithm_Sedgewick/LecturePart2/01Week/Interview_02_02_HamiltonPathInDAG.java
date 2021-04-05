import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

// Q2. Hamiltonian path in a DAG.
// Given a directed acyclic graph, design a linear-time algorithm
// to determine whether it has a Hamiltonian path
// (a simple path that visits every vertex), and if so, find one.

// sol)
public class Interview_02_02_HamiltonPathInDAG {
	private boolean[] marked;
	private Stack<Integer> path;
	
	Interview_02_02_HamiltonPathInDAG(Digraph G) {
		// topological sort
		marked = new boolean[G.V()];
		int s = nonIsolatedVertex(G);
		dfs(G, s);
	}
	
	private static int nonIsolatedVertex(Digraph G) {
		for(int v = 0; v < G.V(); v++)
			if (G.outdegree(v) != 0) return v;
		return -1;
	}
	
	public Iterable<Integer> HamiltonPath() {
		return path;
	}
	
	public static void main(String[] args) {
		// test case 1
		int N1 = 5;
		Digraph G1 = new Digraph(N1);
		G1.addEdge(0, 2);
		G1.addEdge(2, 4);
		G1.addEdge(4, 3);
		G1.addEdge(2, 3);
		G1.addEdge(3, 1);
	}
}
