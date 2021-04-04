import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// Q1. Shortest directed cycle.
// Given a digraph G, design an efficient algorithm to find a directed cycle
// with the minimum number of edges (or report that the graph is acyclic).
// The running time of your algorithm should be at most proportional to V(E + V)
// and use space proportional to E + V,
// where V is the number of vertices and E is the number of edges.

// sol)

public class Interview_02_01_ShortestDirectedCycle {
	private boolean marked[];
	private int[] edgeTo;
	
	private class BreadthFirstDirectedPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private int s;
		
		public BreadthFirstDirectedPaths(Digraph G, int v) {
			bfs(G, v);
		}
		private void bfs(Digraph G, int v) {
			Queue<Integer> q = new Queue<Integer>();
			q.enqueue(v);
			while(!q.isEmpty()) {
				int deq = q.dequeue();
				for(int w : G.adj(deq)) {
					if (!marked[w]) q.enqueue(w);
				}
			}
		}
		public boolean hasPathTo(int w) {
			return marked[w];
		}
	}
	
	Interview_02_01_ShortestDirectedCycle (Digraph G) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		
		for(int v = 0; v < G.V(); v++) { // # of loop: V
			// start vertex v.
			dfs(G, v, v, 0); // # of recursion: E + V
		}
	}
	private void dfs(Digraph G, int v, int start, int count) {
		marked[v] = true;
		
		for(int w : G.adj(v)) {
			if (w == start) {
				StdOut.println(count);
				marked[start] = true;
				edgeTo[start] = v;
			}
			if (!marked[w]) {
				dfs(G, w, start, count++);
				edgeTo[w] = v;				
			}
		}
	}
	Iterable<Integer> findDirectedCycle() {
		
	}
	public static void main(String[] args) {
		int N = 10;
		Digraph G = new Digraph(N);
		Interview_02_01_ShortestDirectedCycle sdc = new Interview_02_01_ShortestDirectedCycle(G);
		for (int v : sdc.findDirectedCycle())
			StdOut.print(v + " ");
		
	}
}
